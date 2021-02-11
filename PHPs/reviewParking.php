<?php
session_start();
include('verifica_login.php');
require_once 'db_function.php';
$db = new DB_Functions();

if($_POST['isAccepted']=="Aceitar"){
if(isset($_POST['idParking'])){
$idParkings=$_POST['idParking'];

for($i=0;$i<sizeof($idParkings);$i++){
$db->acceptParking(intval($idParkings[$i]));
}
        $message="Estacionamento(s) aceite(s) com sucesso";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewParkingHelper.php");
        exit();
} else {
        $message="Selecione pelo menos um estacionamento";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewParkingHelper.php");
        exit();
}
} else {
if(isset($_POST['idParking'])){
$idParkings=$_POST['idParking'];

for($i=0;$i<sizeof($idParkings);$i++){
$db->deleteParking(intval($idParkings[$i]));
}
        $message="Estacionamento(s) eliminado(s) com sucesso";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewParkingHelper.php");
        exit();
} else {
        $message="Selecione pelo menos um estacionamento";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewParkingHelper.php");
        exit();
}

}
?>
