<?php

session_start();
include('verifica_login.php');
require_once 'db_function.php';
$db = new DB_Functions();

if($_POST['isAccepted']=="Aceitar"){

if(isset($_POST['idReviews'])){
$idReviews=$_POST['idReviews'];

for($i=0;$i<sizeof($idReviews);$i++){
$db->acceptParkingComments(intval($idReviews[$i]));
}
        $message="Comentário(s) aceite(s) com sucesso";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewParkingCommentsHelper.php");
        exit();
} else {
        $message="Selecione pelo menos um comentário";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewParkingCommentsHelper.php");
        exit();
}
} else {
if(isset($_POST['idReviews'])){
$idReviews=$_POST['idReviews'];

for($i=0;$i<sizeof($idReviews);$i++){
$db->deleteParkingComments(intval($idReviews[$i]));
}
        $message="Comentário(s) eliminado(s) com sucesso";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewParkingCommentsHelper.php");
        exit();
} else {
        $message="Selecione pelo menos um comentário";
        echo "<script type='text/javascript'>alert('$message');</script>";
        header("refresh:0;url=reviewParkingCommentsHelper.php");
        exit();
}

}
?>
