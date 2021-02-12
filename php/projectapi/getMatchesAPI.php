<?php
require_once 'db_function.php';
$db = new DB_Functions();
    
    //for($j=1;$j<=34;++$j){
    //$uri = 'http://api.football-data.org/v2/competitions/PPL/matches/?matchday='.$j;
    $uri = 'http://api.football-data.org/v2/competitions/PPL/matches?season=2018';
    $reqPrefs['http']['method'] = 'GET';
    $reqPrefs['http']['header'] = 'X-Auth-Token: 5982c11e47ab47029b64c6b5b456c5a3';
    $stream_context = stream_context_create($reqPrefs);
    $response = file_get_contents($uri, false, $stream_context);
    $matches = json_decode($response,true);
    for($i=0;$i<sizeof($matches["matches"]);++$i) {
	$round=intval($matches["matches"][$i]["matchday"]);
	//$round=intval($matches[""]["matchday"]);
	$homeTeam=$matches["matches"][$i]["homeTeam"]["name"];
	$awayTeam=$matches["matches"][$i]["awayTeam"]["name"];
	$date=str_replace('T',' ',$matches["matches"][$i]["utcDate"]);
	$date=str_replace('Z','',$date);
	$new_date= date("Y-m-d H:i:s", strtotime($date . " +1 hours"));
	$score=$matches["matches"][$i]["score"]["fullTime"]["homeTeam"]." - ".$matches["matches"][$i]["score"]["fullTime"]["awayTeam"];
	$db->storeGames($score,1,$new_date,$round,$homeTeam,$awayTeam);
    }
//}		
?>
