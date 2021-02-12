<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response["Game"]= array();

if (isset($_POST["club"])) {

$games = $db->getGames($_POST["club"]);
        if($games != null){
        for($i = 0;$i<sizeof($games);$i++){ 
                $game= (object)array();
		$game->score=$games[$i][0];      
		$auxCompetition=$db->getCompetition($games[$i][1]);
                $game->competition=$auxCompetition;
		$game->timeanddate=$games[$i][2];
		$game->round=$games[$i][3];
                $auxHome=$db->getClubName($games[$i][4]);
                $game->homeTeam=$auxHome;
                $auxAway=$db->getClubName($games[$i][5]);
                $game->awayTeam=$auxAway;
		$auxCoordinates=$db->getStadiumCoordinates($games[$i][6]);
                $game->coordinates=$auxCoordinates;
		$game->stadium=$games[$i][6];
		array_push($response["Game"],$game);
     }
        echo json_encode($response);
        } else {
                $response["error"]=TRUE;
                echo json_encode($response);
        }
} 
?>
