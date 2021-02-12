<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response["Stadium"] = array();

if (isset($_POST["club"])){

$stadiums = $db->getStadiums($_POST["club"]);
        if($stadiums != null){
        for($i = 0;$i<sizeof($stadiums);$i++){
		$stadium= (object) array();       
                $stadium->sName=$stadiums[$i][1];
                $stadium->sClub=$stadiums[$i][2];
                $stadium->sAdress=$stadiums[$i][3];
                $stadium->sYear=$stadiums[$i][4];
                $stadium->sCapacity=$stadiums[$i][5];
                $stadium->sCoordinates=$stadiums[$i][6];
                $stadium->sHistory=$stadiums[$i][7];
		array_push($response["Stadium"],$stadium);
        }
            echo json_encode($response, JSON_UNESCAPED_UNICODE);
        } else {
                $response["error"]=TRUE;
                echo json_encode($response);
        }
}
?>
