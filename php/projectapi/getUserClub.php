<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response = array("error"=>FALSE);

$result = $db->getUserClub(($_POST["email"]));

if($result){
		$response["error"] = FALSE;
		$response["club"] = $result["club"];
		echo json_encode($response);
} else {
	$response["error"] = TRUE;
	echo json_encode($response);
}

?>
