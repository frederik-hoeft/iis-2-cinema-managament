<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:cf15e849-fe0e-4f66-a0a8-73f62a3c4b9e(Cinema.CinemaProject)">
  <persistence version="9" />
  <languages>
    <use id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core" version="2" />
    <use id="9859e7a1-4876-4909-a664-8a214c206698" name="MetaModel" version="0" />
    <use id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage" version="9" />
  </languages>
  <imports />
  <registry>
    <language id="9859e7a1-4876-4909-a664-8a214c206698" name="MetaModel">
      <concept id="1342586541813396118" name="MetaModel.structure.ConstrainedTargetConnector" flags="ng" index="2zC27e">
        <property id="1342586541813396133" name="constraint" index="2zC27X" />
      </concept>
      <concept id="6576532306767764478" name="MetaModel.structure.AttributeConnector" flags="ng" index="2_wlaR" />
      <concept id="3845874433725122187" name="MetaModel.structure.Unidirectional" flags="ng" index="3_FwNw">
        <reference id="3845874433725122188" name="ownerType" index="3_FwNB" />
      </concept>
      <concept id="3845874433725109376" name="MetaModel.structure.Generalisation" flags="ng" index="3_F_VF">
        <reference id="3845874433725109379" name="specific" index="3_F_VC" />
        <reference id="3845874433725109377" name="general" index="3_F_VE" />
      </concept>
      <concept id="3845874433725116463" name="MetaModel.structure.TargetConnector" flags="ng" index="3_FBD4">
        <reference id="3845874433725116464" name="targetType" index="3_FBDr" />
      </concept>
      <concept id="3845874433725116460" name="MetaModel.structure.Connector" flags="ng" index="3_FBD7">
        <property id="3845874433725116461" name="role" index="3_FBD6" />
      </concept>
      <concept id="3845874433725116455" name="MetaModel.structure.Association" flags="ng" index="3_FBDc">
        <child id="3845874433725125412" name="target" index="3_Fx_f" />
        <child id="3845874433725125405" name="owner" index="3_Fx_Q" />
      </concept>
      <concept id="3845874433725012817" name="MetaModel.structure.Service" flags="ng" index="3_Gd4U">
        <child id="3845874433725111961" name="generalisations" index="3_F_jM" />
        <child id="3845874433725012820" name="relationTypes" index="3_Gd4Z" />
      </concept>
      <concept id="3845874433725012776" name="MetaModel.structure.PrimitiveType" flags="ng" index="3_Gd53" />
      <concept id="3845874433725012781" name="MetaModel.structure.Class" flags="ng" index="3_Gd56">
        <property id="3845874433725012782" name="singleton" index="3_Gd55" />
        <child id="6576532306767764487" name="attributes" index="2_wlle" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
  </registry>
  <node concept="3_Gd4U" id="6Kq672PYM_2">
    <property role="TrG5h" value="Cinema" />
    <node concept="3_Gd56" id="6Kq672PYM_3" role="3_Gd4Z">
      <property role="TrG5h" value="Movie" />
      <node concept="2_wlaR" id="6Kq672PYM_5" role="2_wlle">
        <property role="3_FBD6" value="title" />
        <ref role="3_FBDr" node="5H4ykBYMXjQ" resolve="String" />
      </node>
      <node concept="2_wlaR" id="75ZkGyYVW2O" role="2_wlle">
        <property role="3_FBD6" value="description" />
        <ref role="3_FBDr" node="5H4ykBYMXjQ" resolve="String" />
      </node>
    </node>
    <node concept="3_Gd56" id="6Kq672PYPBh" role="3_Gd4Z">
      <property role="TrG5h" value="MovieScreening" />
      <node concept="2_wlaR" id="6Kq672PYPBm" role="2_wlle">
        <property role="3_FBD6" value="finished" />
        <ref role="3_FBDr" node="5H4ykBYMXkd" resolve="Boolean" />
      </node>
      <node concept="2_wlaR" id="75ZkGyYVZ4Y" role="2_wlle">
        <property role="3_FBD6" value="Name" />
        <ref role="3_FBDr" node="5H4ykBYMXjQ" resolve="String" />
      </node>
    </node>
    <node concept="3_Gd56" id="6Kq672PYPBu" role="3_Gd4Z">
      <property role="TrG5h" value="CinemaHall" />
      <node concept="2_wlaR" id="6Kq672PYPB_" role="2_wlle">
        <property role="3_FBD6" value="available" />
        <ref role="3_FBDr" node="5H4ykBYMXkd" resolve="Boolean" />
      </node>
    </node>
    <node concept="3_Gd56" id="6Kq672PYPBN" role="3_Gd4Z">
      <property role="TrG5h" value="SeatRow" />
    </node>
    <node concept="3_Gd56" id="6Kq672PYPCi" role="3_Gd4Z">
      <property role="TrG5h" value="Seat" />
    </node>
    <node concept="3_Gd56" id="6Kq672PYPCL" role="3_Gd4Z">
      <property role="TrG5h" value="BookingState" />
    </node>
    <node concept="3_Gd56" id="6Kq672PYPDR" role="3_Gd4Z">
      <property role="TrG5h" value="Booking" />
    </node>
    <node concept="3_Gd56" id="6Kq672PYPEp" role="3_Gd4Z">
      <property role="TrG5h" value="Reservation" />
    </node>
    <node concept="3_Gd56" id="6Kq672PYPFj" role="3_Gd4Z">
      <property role="TrG5h" value="Customer" />
      <node concept="2_wlaR" id="6Kq672PYPFH" role="2_wlle">
        <property role="3_FBD6" value="firstName" />
        <ref role="3_FBDr" node="5H4ykBYMXjQ" resolve="String" />
      </node>
      <node concept="2_wlaR" id="6Kq672PYPFK" role="2_wlle">
        <property role="3_FBD6" value="lastName" />
        <ref role="3_FBDr" node="5H4ykBYMXjQ" resolve="String" />
      </node>
      <node concept="2_wlaR" id="6Kq672PYPFO" role="2_wlle">
        <property role="3_FBD6" value="email" />
        <ref role="3_FBDr" node="5H4ykBYMXjQ" resolve="String" />
      </node>
    </node>
    <node concept="3_Gd56" id="6Kq672PYPGl" role="3_Gd4Z">
      <property role="TrG5h" value="PriceCategory" />
      <node concept="2_wlaR" id="6Kq672PYPGO" role="2_wlle">
        <property role="3_FBD6" value="price" />
        <property role="2zC27X" value="1axOPyL0vao/PartialMap" />
        <ref role="3_FBDr" node="5H4ykBYMXk3" resolve="Rational" />
      </node>
    </node>
    <node concept="3_Gd56" id="6Kq672PYPH_" role="3_Gd4Z">
      <property role="TrG5h" value="PriceCategoryBox" />
      <property role="3_Gd55" value="true" />
    </node>
    <node concept="3_Gd56" id="6Kq672PYPIG" role="3_Gd4Z">
      <property role="TrG5h" value="PriceCategoryServiceBox" />
      <property role="3_Gd55" value="true" />
    </node>
    <node concept="3_Gd56" id="6Kq672PYPJN" role="3_Gd4Z">
      <property role="TrG5h" value="PriceCategoryStalls" />
      <property role="3_Gd55" value="true" />
    </node>
    <node concept="3_FBDc" id="6Kq672PYPLk" role="3_Gd4Z">
      <property role="TrG5h" value="Movie_MovieScreening" />
      <node concept="2zC27e" id="6xkQ0BQzlvt" role="3_Fx_f">
        <property role="3_FBD6" value="screenings" />
        <property role="2zC27X" value="1axOPyL0vaw/Set" />
        <ref role="3_FBDr" node="6Kq672PYPBh" resolve="MovieScreening" />
      </node>
      <node concept="3_FwNw" id="3Kw_u$R$aeZ" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYM_3" resolve="Movie" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$akZ" role="3_Gd4Z">
      <property role="TrG5h" value="MovieScreeninig_Movie" />
      <node concept="3_FwNw" id="3Kw_u$R$alI" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPBh" resolve="MovieScreening" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$alL" role="3_Fx_f">
        <property role="3_FBD6" value="movie" />
        <ref role="3_FBDr" node="6Kq672PYM_3" resolve="Movie" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$amv" role="3_Gd4Z">
      <property role="TrG5h" value="MovieScreening_BookingState" />
      <node concept="3_FwNw" id="3Kw_u$R$anh" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPBh" resolve="MovieScreening" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$ank" role="3_Fx_f">
        <property role="3_FBD6" value="bookingStates" />
        <property role="2zC27X" value="1axOPyL0vaw/Set" />
        <ref role="3_FBDr" node="6Kq672PYPCL" resolve="BookingState" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$ao5" role="3_Gd4Z">
      <property role="TrG5h" value="BookingState_MovieScreening" />
      <node concept="3_FwNw" id="3Kw_u$R$aoU" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPCL" resolve="BookingState" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$aoX" role="3_Fx_f">
        <property role="3_FBD6" value="screening" />
        <ref role="3_FBDr" node="6Kq672PYPBh" resolve="MovieScreening" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$apL" role="3_Gd4Z">
      <property role="TrG5h" value="MovieScreening_CinemaHall" />
      <node concept="3_FwNw" id="3Kw_u$R$aqD" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPBh" resolve="MovieScreening" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$aqG" role="3_Fx_f">
        <property role="3_FBD6" value="hall" />
        <ref role="3_FBDr" node="6Kq672PYPBu" resolve="CinemaHall" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$arz" role="3_Gd4Z">
      <property role="TrG5h" value="CinemaHall_SeatRow" />
      <node concept="3_FwNw" id="3Kw_u$R$asu" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPBu" resolve="CinemaHall" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$asx" role="3_Fx_f">
        <property role="3_FBD6" value="rows" />
        <property role="2zC27X" value="1axOPyL0vaw/Set" />
        <ref role="3_FBDr" node="6Kq672PYPBN" resolve="SeatRow" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$atr" role="3_Gd4Z">
      <property role="TrG5h" value="SeatRow_CinemaHall" />
      <node concept="3_FwNw" id="3Kw_u$R$aup" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPBN" resolve="SeatRow" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$aus" role="3_Fx_f">
        <property role="3_FBD6" value="hall" />
        <ref role="3_FBDr" node="6Kq672PYPBu" resolve="CinemaHall" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$avp" role="3_Gd4Z">
      <property role="TrG5h" value="SeatRow_PriceCategory" />
      <node concept="3_FwNw" id="3Kw_u$R$awq" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPBN" resolve="SeatRow" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$awt" role="3_Fx_f">
        <property role="3_FBD6" value="price" />
        <ref role="3_FBDr" node="6Kq672PYPGl" resolve="PriceCategory" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$axt" role="3_Gd4Z">
      <property role="TrG5h" value="SeatRow_Seat" />
      <node concept="3_FwNw" id="3Kw_u$R$ayx" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPBN" resolve="SeatRow" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$ay$" role="3_Fx_f">
        <property role="3_FBD6" value="seats" />
        <property role="2zC27X" value="1axOPyL0vaw/Set" />
        <ref role="3_FBDr" node="6Kq672PYPCi" resolve="Seat" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$azB" role="3_Gd4Z">
      <property role="TrG5h" value="Seat_SeatRow" />
      <node concept="3_FwNw" id="3Kw_u$R$a$I" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPCi" resolve="Seat" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$a$L" role="3_Fx_f">
        <property role="3_FBD6" value="row" />
        <ref role="3_FBDr" node="6Kq672PYPBN" resolve="SeatRow" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$aCd" role="3_Gd4Z">
      <property role="TrG5h" value="BookingState_Seat" />
      <node concept="3_FwNw" id="3Kw_u$R$aDq" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPCL" resolve="BookingState" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$aDt" role="3_Fx_f">
        <property role="3_FBD6" value="seat" />
        <ref role="3_FBDr" node="6Kq672PYPCi" resolve="Seat" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$aED" role="3_Gd4Z">
      <property role="TrG5h" value="BookingState_Customer" />
      <node concept="3_FwNw" id="3Kw_u$R$aFT" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPCL" resolve="BookingState" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$aFW" role="3_Fx_f">
        <property role="3_FBD6" value="customer" />
        <ref role="3_FBDr" node="6Kq672PYPFj" resolve="Customer" />
      </node>
    </node>
    <node concept="3_FBDc" id="3Kw_u$R$aHb" role="3_Gd4Z">
      <property role="TrG5h" value="Customer_BookingState" />
      <node concept="3_FwNw" id="3Kw_u$R$aIu" role="3_Fx_Q">
        <ref role="3_FwNB" node="6Kq672PYPFj" resolve="Customer" />
      </node>
      <node concept="2zC27e" id="3Kw_u$R$aIx" role="3_Fx_f">
        <property role="3_FBD6" value="bookings" />
        <property role="2zC27X" value="1axOPyL0vaw/Set" />
        <ref role="3_FBDr" node="6Kq672PYPCL" resolve="BookingState" />
      </node>
    </node>
    <node concept="3_F_VF" id="6Kq672PYPKp" role="3_F_jM">
      <ref role="3_F_VE" node="6Kq672PYPCL" resolve="BookingState" />
      <ref role="3_F_VC" node="6Kq672PYPDR" resolve="Booking" />
    </node>
    <node concept="3_F_VF" id="6Kq672PYPKr" role="3_F_jM">
      <ref role="3_F_VE" node="6Kq672PYPCL" resolve="BookingState" />
      <ref role="3_F_VC" node="6Kq672PYPEp" resolve="Reservation" />
    </node>
    <node concept="3_F_VF" id="6Kq672PYPKu" role="3_F_jM">
      <ref role="3_F_VE" node="6Kq672PYPGl" resolve="PriceCategory" />
      <ref role="3_F_VC" node="6Kq672PYPH_" resolve="PriceCategoryBox" />
    </node>
    <node concept="3_F_VF" id="6Kq672PYPKy" role="3_F_jM">
      <ref role="3_F_VE" node="6Kq672PYPGl" resolve="PriceCategory" />
      <ref role="3_F_VC" node="6Kq672PYPIG" resolve="PriceCategoryServiceBox" />
    </node>
    <node concept="3_F_VF" id="6Kq672PYPKB" role="3_F_jM">
      <ref role="3_F_VE" node="6Kq672PYPGl" resolve="PriceCategory" />
      <ref role="3_F_VC" node="6Kq672PYPJN" resolve="PriceCategoryStalls" />
    </node>
  </node>
  <node concept="3_Gd4U" id="5H4ykBYMXjP">
    <property role="TrG5h" value=" baseTypes" />
    <node concept="3_Gd53" id="5H4ykBYMXjQ" role="3_Gd4Z">
      <property role="TrG5h" value="String" />
    </node>
    <node concept="3_Gd53" id="5H4ykBYMXjV" role="3_Gd4Z">
      <property role="TrG5h" value="Integer" />
    </node>
    <node concept="3_Gd53" id="5H4ykBYMXk3" role="3_Gd4Z">
      <property role="TrG5h" value="Rational" />
    </node>
    <node concept="3_Gd53" id="5H4ykBYMXkd" role="3_Gd4Z">
      <property role="TrG5h" value="Boolean" />
    </node>
  </node>
</model>

