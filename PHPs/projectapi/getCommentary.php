<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response["Commentary"] = array();

if (isset($_POST["idParking"])){

$comments= $db->getComments($_POST["idParking"]);
	if($comments!=null){
	for($i=0;$i<sizeof($comments);$i++){
	$comment=(object) array();
	$comment->date=$comments[$i][3];
	$comment->comment=$comments[$i][2];
	$comment->aSpots=intval($comments[$i][1]);
	array_push($response["Commentary"],$comment);
	}
	echo json_encode($response,JSON_UNESCAPED_UNICODE);
        } else {
                $response["error"]=TRUE;
                echo json_encode($response);
        }
}
