<?php
require_once 'db_function.php';
$db = new DB_Functions();
    
    //$uri = 'http://api.football-data.org/v2/competitions/PPL/standings';
    $uri = 'http://api.football-data.org/v2/competitions/PPL/standings?season=2018';
    $reqPrefs['http']['method'] = 'GET';
    $reqPrefs['http']['header'] = 'X-Auth-Token: 5982c11e47ab47029b64c6b5b456c5a3';
    $stream_context = stream_context_create($reqPrefs);
    $response = file_get_contents($uri, false, $stream_context);
    $standings = json_decode($response,true);
    for($i=0;$i<sizeof($standings["standings"][0]["table"]);++$i) {
	$club=$standings["standings"][0]["table"][$i]["team"]["name"];
	$position=$standings["standings"][0]["table"][$i]["position"];
	$gamesPlayed=$standings["standings"][0]["table"][$i]["playedGames"];
	$points=$standings["standings"][0]["table"][$i]["points"];
	$goals=$standings["standings"][0]["table"][$i]["goalsFor"].":".$standings["standings"][0]["table"][$i]["goalsAgainst"];
	$db->updateStandings($club,$points,$gamesPlayed,$goals,$position);
    }
?>
