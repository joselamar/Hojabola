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
} elseif(empty($_POST["email"])){
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira um e-mail";
	echo json_encode($response);
}elseif(!filter_var($_POST['email'],FILTER_VALIDATE_EMAIL)){
                $response["error"] = TRUE;
                $response["error_msg"] = "Insira um e-mail válido";
                echo json_encode($response);
} elseif($db->doesEmailExist(($_POST['email']))){
		$response["error"] = TRUE;
		$response["error_msg"] = "Utilizador já existe com o e-mail indicado ".$_POST['email'];
		echo json_encode($response);
} elseif(empty($_POST["password"])){
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira uma password";
	echo json_encode($response);
} elseif(strlen(($_POST["password"])) <= 4){
        $response["error"] = TRUE;
        $response["error_msg"] = "Insira uma password com 5 ou mais caracteres";
        echo json_encode($response);
} elseif(($_POST['password']) != ($_POST['confirmpassword'])){
	$response["error"] = TRUE;
	$response["error_msg"] = "Passwords não coincidem";
	echo json_encode($response);
} else echo json_encode($response)				
?>