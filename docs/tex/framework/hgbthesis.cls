\NeedsTeXFormat{LaTeX2e}
\newcommand{\hgbthesisDate}{2013/05/17} %Versionsdatum
\ProvidesClass{framework/hgbthesis}[\hgbthesisDate\space LaTeX-Vorlage fuer Praxisarbeiten und Bachelorarbeiten etc.]

\RequirePackage{ifthen}
\RequirePackage{scrextend} %used in glossary

\newcommand{\hgb@MainLanguage}{english}	% also referenced in hgb.sty
\newcommand{\@patitelseiten}{}
\newcommand{\@bachelortitelseiten}{}
\newcommand{\@maketitlepages}{\@patitelseiten}
\newcommand{\beidseitig}{twoside}

\newcounter{minpages}
\newcounter{maxpages}
\newcounter{countingpage}

%% begin class options ---------------------------------------------
\DeclareOption{praxisarbeit}{\renewcommand{\@maketitlepages}{\@patitelseiten}}
\DeclareOption{bachelorthesis}{\renewcommand{\@maketitlepages}{\@bachelortitelseiten}}
\DeclareOption{english}{\renewcommand{\hgb@MainLanguage}{english}}
\DeclareOption{oneside}{\renewcommand{\beidseitig}{oneside}}
\DeclareOption{twoside}{\renewcommand{\beidseitig}{twoside}}
\DeclareOption*{\ClassWarning{hgbthesis}{Unknown document option "\CurrentOption"}}
\ProcessOptions
%% end class options ---------------------------------------------
 
\LoadClass[a4paper,12pt,\beidseitig,openany]{book}
 
\usepackage{framework/hgb} 

%-------------- Setup für Seiten im Front-Teil --------------------

\newcommand{\@studiengang}{\@MissingArg{Studiengang??}}
\newcommand{\studiengang}[1]{%
	\renewcommand{\@studiengang}{#1}
}

\newcommand{\@pruefer}{\@MissingArg{Pruefer??}}
\newcommand{\pruefer}[1]{%
	\renewcommand{\@pruefer}{#1}
}

\newcommand{\@prueferBack}{\@MissingArg{Pruefer??}}
\newcommand{\prueferBack}[1]{%
	\renewcommand{\@prueferBack}{#1}
}

\newcommand{\@praxisquartal}{\@MissingArg{praxisquartal??}}
\newcommand{\praxisquartal}[1]{%
	\renewcommand{\@praxisquartal}{#1}
}

\newcommand{\@theoriequartal}{\@MissingArg{theoriequartal??}}
\newcommand{\theoriequartal}[1]{%
	\renewcommand{\@theoriequartal}{#1}
}

\newcommand{\@authorstreet}{\@MissingArg{authorstreet??}}
\newcommand{\authorstreet}[1]{%
	\renewcommand{\@authorstreet}{#1}
}

\newcommand{\@authortown}{\@MissingArg{authortown??}}
\newcommand{\authortown}[1]{%
	\renewcommand{\@authortown}{#1}
}


\newcommand{\@studienort}{\@MissingArg{Studienort??}}
\newcommand{\studienort}[1]{%
	\renewcommand{\@studienort}{#1}
}

\newcommand{\@betreuerBez}{????}
\newcommand{\@betreuerName}{\@MissingArg{Betreuer??}}

\newcommand{\betreuer}[1]{%
  \renewcommand{\@betreuerBez}{Betreuer}
	\renewcommand{\@betreuerName}{#1}
}
\newcommand{\betreuerin}[1]{%
  \renewcommand{\@betreuerBez}{Betreuerin}
	\renewcommand{\@betreuerName}{#1}
}

\newcommand{\@nummer}{\@MissingArg{Arbeit Nummer??}}
\newcommand{\nummer}[1]{%
	\renewcommand{\@nummer}{#1}
}

\newcommand{\@gegenstand}{\@MissingArg{Gegenstand??}}
\newcommand{\gegenstand}[1]{%
	\renewcommand{\@gegenstand}{#1}
}

\newcommand{\@semester}{\@MissingArg{Semester??}}
\newcommand{\semester}[1]{%
	\renewcommand{\@semester}{#1}
}
%%----- Exporte für Nutzung in der Erklärung -------

\def\getAuthor{\@author}
\def\getStudienort{\@studienort}

%% -------------------------------------------------

\newcounter{@abgabetag}
\newcounter{@abgabemonat}
\newcounter{@abgabejahr}

\setcounter{@abgabetag}{\the\day}
\setcounter{@abgabemonat}{\the\month}
\setcounter{@abgabejahr}{\the\year}

\newcommand{\abgabedatum}[3]{%	%\abgabedatum{yyyy}{mm}{dd}
	\setcounter{@abgabejahr}{#1}
	\setcounter{@abgabemonat}{#2}
	\setcounter{@abgabetag}{#3}
}
\newcommand{\abgabemonat}[1]{%
	\ClassWarning{hgbthesis}{\noexpand\abgabemonat is deprecated - 
	use \noexpand\abgabedatum instead!}
}

\newcommand{\abgabejahr}[1]{%
	\ClassWarning{hgbthesis}{\noexpand\abgabejahr is deprecated - 
	use \noexpand\abgabedatum instead!}
}

\newcommand{\dateabgabe}{\formatdate{\value{@abgabetag}}{\value{@abgabemonat}}{\value{@abgabejahr}}}

%% ----------------------------------------------------------

\newcommand{\@firma}{\@MissingArg{Firma??}}
\newcommand{\firma}[1]{%
\renewcommand{\@firma}{#1}
}

\newcommand{\@firmenUrl}{\@MissingArg{www.???}}
\newcommand{\firmenUrl}[1]{%
\renewcommand{\@firmenUrl}{#1}
}

\newcommand{\@firmenTel}{\@MissingArg{Tel??}}
\newcommand{\firmenTel}[1]{%
\renewcommand{\@firmenTel}{#1}
}

\newcommand{\@license}{\@MissingArg{Lizenztext??}}
\newcommand{\license}[1]{%
\renewcommand{\@license}{#1}
}


%------------------ Titelseiten -------------------

\newcommand{\@bachelortitel}{%
\begin{titlepage}%
\begin{english}
\rule{0mm}{1mm}
\begin{center}%
  \setlength{\unitlength}{1cm}
  \hskip -5mm
  \includegraphics[height=3cm]{Logo_FHDW.pdf}
  \vskip 8mm
    Fachhochschule für die Wirtschaft Hannover
    \vskip 0mm
    {- FHDW -}
    \vskip 0mm
    Hannover
    \vskip 8mm
    {\Large B\,A\,C\,H\,E\,L\,O\,R\, T\,H\,E\,S\,I\,S}%
    \vskip 8mm
    Thema:
    \vskip 2mm
   {\parbox{14cm}{\centering\LARGE%
    \setlength{\baselineskip}{20pt}%
    \@title}}
    \vskip 8mm%
    Erstprüfer:
    \vskip 1mm
    {\large \@pruefer}%
    \vskip 8mm%
    Zweitprüfer:
    \vskip 1mm
    {\large \@prueferBack}%
    \vskip 8mm%
    Verfasser:
    \vskip 1mm
    {\large \@author}
    \vskip 0mm
    {\large \@authorstreet}
    \vskip 0mm
    {\large \@authortown}
    \vskip 8mm
    {\sc\@praxisquartal}. Praxisquartal
    \vskip 1mm
    Studiengang: {\large\sc\@studiengang}%
    \vskip 8mm
    Eingereicht am:
    \vskip 1mm
    {\large \dateabgabe}
\end{center}\par
  \vfil\null
\end{english}
\end{titlepage}%
}

\newcommand{\@patitel}{%
\begin{titlepage}%
\begin{english}
\rule{0mm}{1mm}
\begin{center}%
  \setlength{\unitlength}{1cm}
  \hskip -5mm
  \hskip 10mm
  \includegraphics[height=3cm]{Logo_FHDW.pdf}
  \vskip 10mm
    Fachhochschule für die Wirtschaft Hannover
    \vskip 0mm
    {- FHDW -}
    \\Hausarbeit in: Informationsinfrastrukturen 2
    \vskip 15mm
    Thema:
    \vskip 2mm
   {\parbox{12cm}{\centering\LARGE%
    \setlength{\baselineskip}{20pt}%
    \@title}}
    \vskip 10mm%
    Prüfer:
    \vskip 2mm
    {\large \@pruefer}%
    \vskip 2mm
    {\large \@prueferBack}%
    \vskip 10mm%
    Verfasser:
    \vskip 2mm
    {\large \@author}
    \vskip 0mm
    {\large \@authorstreet}
    \vskip 0mm
    {\large \@authortown}
    \vskip 10mm
    %{\sc\@theoriequartal}. Theoriequartal
    \vskip 2mm
    Studiengang {\@studiengang}%
    \vskip 10mm
    Eingereicht am:
    \vskip 2mm
    {\large \ifnum\value{@abgabetag}<10 0\fi\arabic{@abgabetag}.\ifnum\value{@abgabemonat}<10 0\fi\arabic{@abgabemonat}.\arabic{@abgabejahr}}
    % {\large {@abgabe}}
    
\end{center}
%\par\vfil\null %Hier war das mal (:
\end{english}
\end{titlepage}%
}



%------------ Struktur der Frontseiten -------------------

\renewcommand{\@patitelseiten}{
\@patitel
\setcounter{minpages}{20}
\setcounter{maxpages}{20}
}

\renewcommand{\@bachelortitelseiten}{
\@bachelortitel
\setcounter{minpages}{40}
\setcounter{maxpages}{44}
}

%------------ Automatischer Eintrag der PDF Metadaten ------------

\renewcommand{\maketitle}{	%replace standard LaTeX-command
\hypersetup{
		pdftitle={\@title},
		pdfauthor={\@author},
		pdfsubject={\@studiengang, \@studienort},
		pdfcreator={wkg},
		pdfproducer={},
		pdfkeywords={}
}%
\newgeometry{
    left=20mm,
    right=20mm,
    top=20mm,
    bottom=20mm,
    heightrounded
}
\@maketitlepages
\restoregeometry
}

%%-------------------------------------------------------------

\usepackage{geometry}
\geometry{
    left=30mm, % official: 3-4cm
    right=20mm, % official: 4-5cm
    top=25mm, % official: 4-5cm
    bottom=25mm, % official: 4-5cm
    bindingoffset=0mm, %Der Rand sollte eigentlich nach FHDW vorgaben 20mm sein
    heightrounded
}

\usepackage{framework/hgbheadings}
\usepackage{framework/hgbbib}
\usepackage{framework/hgbtodo}
\usepackage{framework/common-commands}
\usepackage{default/owncommands}
\usepackage{default/optional-packages}


%%---------------------- Seitenzahlen Warning ----------------------
% \newboolean{pagewarning}
% \setboolean{pagewarning}{true}
% \newcommand{\pagewarning}[1]{\setboolean{pagewarning}{#1}}

% \EveryShipout{ \if@mainmatter \stepcounter{countingpage} \fi}

% \AtEndDocument{
% \ifthenelse{\boolean{pagewarning}}{
%     \ifthenelse{\thecountingpage<\theminpages \OR \thecountingpage>\themaxpages}{
%         \PackageWarning{hgbthesis}{Du hast zu viele oder wenige Seiten geschrieben! (Seiten: \thecountingpage)}
%     }{}
% }{}
% }
%%-------------------------------------------------------------

\endinput

