<?php
session_start();

require_once 'db_function.php';
$db = new DB_Functions();

if(empty($_POST['username']) || empty($_POST['password'])) {
	header('Location: index.php');
	$_SESSION['not_loged'] = true;
	exit();
}

$user = $db->isAdmin($_POST['username'],$_POST['password']);


if($user != false) {
	$_SESSION['username'] = $_POST['username'];
	header('Location: painel.php');
	exit();
} else {
	$_SESSION['not_loged'] = true;
	header('Location: index.php');
	exit();
}

?>
