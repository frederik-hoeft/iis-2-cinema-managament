$output_dir="\bin\Release\net7.0\publish"
$linux_profile="linux-x64-embed"
$windows_profile="win-x64-embed"
$osx_profile="osx-x64-embed"

$client_dir="IIS.Client"
$client_output_dir="..\..\$($client_dir)$($output_dir)"

# clean output dir
Remove-Item -Path ..\$output_dir\* -Recurse

# publish IIS.Client.Interactive
dotnet publish -c Release -p:PublishProfile=$linux_profile ..\IIS.Client.Interactive.csproj
dotnet publish -c Release -p:PublishProfile=$osx_profile ..\IIS.Client.Interactive.csproj
dotnet publish -c Release -p:PublishProfile=$windows_profile ..\IIS.Client.Interactive.csproj

# remove debug files
Remove-Item -Path ..\$output_dir\$linux_profile\*.pdb
Remove-Item -Path ..\$output_dir\$osx_profile\*.pdb
Remove-Item -Path ..\$output_dir\$windows_profile\*.pdb

# remove .runtimeconfig.json files
Remove-Item -Path ..\$output_dir\$linux_profile\*.runtimeconfig.json
Remove-Item -Path ..\$output_dir\$osx_profile\*.runtimeconfig.json
Remove-Item -Path ..\$output_dir\$windows_profile\*.runtimeconfig.json

# remove output dir of IIS.Client
Remove-Item -Path $client_output_dir\* -Recurse

# publish IIS.Client
dotnet publish -c Release -p:PublishProfile=$linux_profile ..\..\$client_dir\IIS.Client.csproj
dotnet publish -c Release -p:PublishProfile=$osx_profile ..\..\$client_dir\IIS.Client.csproj
dotnet publish -c Release -p:PublishProfile=$windows_profile ..\..\$client_dir\IIS.Client.csproj

# remove debug files of IIS.Client
Remove-Item -Path $client_output_dir\$linux_profile\*.pdb
Remove-Item -Path $client_output_dir\$osx_profile\*.pdb
Remove-Item -Path $client_output_dir\$windows_profile\*.pdb

# copy published IIS.Client to IIS.Client.Interactive output dir
Copy-Item -Path $client_output_dir\$linux_profile\* -Destination ..\$output_dir\$linux_profile\
Copy-Item -Path $client_output_dir\$osx_profile\* -Destination ..\$output_dir\$osx_profile\
Copy-Item -Path $client_output_dir\$windows_profile\* -Destination ..\$output_dir\$windows_profile\

# zip IIS.Client.Interactive
Compress-Archive -LiteralPath ..\$output_dir\$linux_profile\ -DestinationPath ..\$output_dir\$linux_profile.zip
Compress-Archive -LiteralPath ..\$output_dir\$osx_profile\ -DestinationPath ..\$output_dir\$osx_profile.zip
Compress-Archive -LiteralPath ..\$output_dir\$windows_profile\ -DestinationPath ..\$output_dir\$windows_profile.zip

Invoke-Item ..\$output_dir

Stop-Process -Id $PID