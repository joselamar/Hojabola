<?php
session_start();
include('verifica_login.php');
require_once 'db_function.php';
$db = new DB_Functions();

if($_POST['isAccepted']=="Aceitar"){

if(isset($_POST['idReviews'])){
$idReviews=$_POST['idReviews'];

for($i=0;$i<sizeof($idReviews);$i++){
$db->acceptFoodPlaceComments(intval($idReviews[$i]));
}
        $message="Crítica(s) aceite(s) com sucesso";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewFoodPlacesHelper.php");
        exit();
} else {
        $message="Selecione pelo menos uma crítica";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewFoodPlacesHelper.php");
        exit();
}
} else {
if(isset($_POST['idReviews'])){
$idReviews=$_POST['idReviews'];

for($i=0;$i<sizeof($idReviews);$i++){
$db->deleteFoodPlaceComments(intval($idReviews[$i]));
}
        $message="Crítica(s) eliminada(s) com sucesso";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewFoodPlacesHelper.php");
        exit();
} else {
        $message="Selecione pelo menos uma crítica";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewFoodPlacesHelper.php");
        exit();
}

}
?>
