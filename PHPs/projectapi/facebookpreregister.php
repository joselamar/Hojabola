<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response = array("error"=>FALSE);

if(empty($_POST["name"])){
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira o nome de utilizador";
	echo json_encode($response);
} elseif($db->doesNameExist($_POST["name"])){
		$response["error"] = TRUE;
		$response["error_msg"] = "Nome de utilizador já em uso";
		echo json_encode($response);
} else echo json_encode($response);