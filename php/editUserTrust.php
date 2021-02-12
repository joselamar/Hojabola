<?php
session_start();

include('verifica_login.php');
require_once 'db_function.php';
$db = new DB_Functions();

if(empty($_POST['username'])) {
	$message = "Preencha os campos todos!";
        echo "<script type='text/javascript'>alert('$message');</script>";
	header( "refresh:0;url=editUserTrustHelper.php" );
	exit();
}

$user = $db->isUser($_POST['username']);

if($db->isAdminUser($_POST['username'])){
	 $message = "Não pode alterar o seu próprio nível de confiança";
        echo "<script type='text/javascript'>alert('$message');</script>";
                header( "refresh:0;url=editUserTrustHelper.php" );
        exit();
}

if ($user == false) {
        $message = "Este username não existe";
        echo "<script type='text/javascript'>alert('$message');</script>";
	 	header( "refresh:0;url=editUserTrustHelper.php" );
        exit();
} else {
	if($db->updateTrust($_POST['username'],$_POST['trust'])){
	$message = "Nível de Confiança editado com sucesso";
	echo "<script type='text/javascript'>alert('$message');</script>";
	 header( "refresh:0;url=painel.php" );
        exit();
	} else {
	$message = "Erro ao editar nível de confiança!";
        echo "<script type='text/javascript'>alert('$message');</script>";
         header( "refresh:0;url=editUserTrustHelper.php" );
        exit();

	}
}

?>






