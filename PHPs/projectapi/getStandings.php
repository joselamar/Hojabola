<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response["Standings"] = array();

if (isset($_POST["club"])){

$standings = $db->getStandings();
        if($standings != null){
        for($i = 0;$i<sizeof($standings);$i++){
                $standing= (object) array();       
                $standing->position=$standings[$i][5];
		$clubName=$db->getClubName($standings[$i][1]);
                $standing->clubName=$clubName;
                $standing->gamesPlayed=$standings[$i][3];
                $standing->goals=$standings[$i][4];
                $standing->points=$standings[$i][2];
                array_push($response["Standings"],$standing);
        }
            echo json_encode($response, JSON_UNESCAPED_UNICODE);
        } else {
                $response["error"]=TRUE;
                echo json_encode($response);
        }
}
?>
