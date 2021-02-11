<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response["Parking"] = array();

if (isset($_POST["idStadium"])){

$parkings = $db->getParkings($_POST["idStadium"]);
        if($parkings != null){
        for($i = 0;$i<sizeof($parkings);$i++){
                $parking= (object) array();       
		$parking->idParking=$parkings[$i][0];
                $parking->pName=$parkings[$i][1];
                $parking->pAdress=$parkings[$i][2];
                $parking->pSpots=intval($parkings[$i][3]);
                $parking->pAvailable=intval($parkings[$i][4]);
                $parking->pCoordinates=$parkings[$i][6];
                $parking->isPayed=intval($parkings[$i][7]);
		if($parkings[$i][8]!="NULL"){
                $imagedata = file_get_contents($parkings[$i][8]);
                $imagein64 = base64_encode($imagedata);
                $parking->image=$imagein64;
		} else {
		$parking->image="NULL";
		}
                array_push($response["Parking"],$parking);
        }
            echo json_encode($response, JSON_UNESCAPED_UNICODE);
        } else {
                $response["error"]=TRUE;
                echo json_encode($response);
        }
}
?>

