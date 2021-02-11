<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response["FoodPlaces"] = array();

if (isset($_POST["idStadium"])){

$foodplaces = $db->getFoodPlaces($_POST["idStadium"]);
        if($foodplaces != null){
        for($i = 0;$i<sizeof($foodplaces);$i++){
                $foodplace= (object) array();
		$foodplace->idFoodPlace=$foodplaces[$i][0];       
                $foodplace->fName=$foodplaces[$i][1];
                $foodplace->fClassification=$foodplaces[$i][2];
                $foodplace->fCoordinates=$foodplaces[$i][4];
                $foodplace->fDistance=$foodplaces[$i][3];
                $foodplace->fCommentary=array();
        	$comments= $db->getFoodPlaceComments($foodplaces[$i][0]);
		if($comments!=null){
			for($j=0;$j<sizeof($comments);$j++){
				$comment=(object) array();
                	        $comment->date=$comments[$j][4];
                       		$comment->comment=$comments[$j][3];
                        	$comment->classification=$comments[$j][2];
                        	array_push($foodplace->fCommentary,$comment);
			}
		}
                array_push($response["FoodPlaces"],$foodplace);
        }
            echo json_encode($response, JSON_UNESCAPED_UNICODE);
        } else {
                $response["error"]=TRUE;
                echo json_encode($response);
        }
}
?>

