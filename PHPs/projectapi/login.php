<?php

require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response = array("error"=>FALSE);

if(empty($_POST["name"])){
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira o nome de utilizador";
	echo json_encode($response);
} elseif(!($db->doesNameExist($_POST["name"]))){
		$response["error"] = TRUE;
		$response["error_msg"] = "Insira um nome de utilizador válido";
		echo json_encode($response);
} elseif(empty($_POST["password"])){
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira a password";
	echo json_encode($response);
} elseif(isset($_POST['name']) && isset($_POST['password'])) {
	$name = $_POST['name'];
	$password = $_POST['password'];

	$user = $db->getUserByNameAndPassword($name,$password);

	if($user != false){
		$response["error"]= FALSE;
		$response["idUser"] = $user["idUser"];
		$response["user"]["uName"] = $user["uName"];
		$response["user"]["uEmail"] = $user["uEmail"];
		$response["user"]["has_idHas"] = $user["has_idHas"];
		echo json_encode($response);
		} else {
			$response["error"] = TRUE;
			$response["error_msg"] = "Login errado";
			echo json_encode($response);
		} 
}

?>
