<?php
require_once 'db_function.php';

$db = new DB_Functions();
//json response
$response = array("error"=>FALSE);

if($db->doesEmailExist(($_POST['email']))){
		$response["error"] = TRUE;
		echo json_encode($response);
} else {
	$response["error"] = FALSE;
	echo json_encode($response);
}



?>