<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response = array("error"=>FALSE);
$flag=0;

if (empty($_POST["aSpots"])){
        $flag=1;
        $response["error"] = TRUE;
        $response["error_msg"] = "Insira uma estimativa de lugares disponíveis";
        echo json_encode($response);
} elseif (empty($_POST["commentary"])) {
        $flag=1;
        $response["error"] = TRUE;
        $response["error_msg"] = "Insira o seu comentário";
        echo json_encode($response);
} elseif (strlen($_POST["commentary"])>200){
	$flag=1;
        $response["error"] = TRUE;
        $response["error_msg"] = "Excedeu os 200 caracteres. Diminua o seu comentário";
	echo json_encode($response);
}

if($flag==0){
	date_default_timezone_get("Europe/London");
	$data = date("Y-m-d H:i:s");
	$date = date("Y-m-d H:i:s", strtotime($data . " -1 hours"));
	$commentary=$db->storeCommentary($_POST["idParking"],intval($_POST["aSpots"]),$date,$_POST["commentary"]);
	if($commentary){
		 $response["error"] = FALSE;
                 $response["error_msg"] = "Comentário foi adicionado e vai ser revisto";
                 echo json_encode($response);
	}else {
	$response["error"] = TRUE;
        $response["error_msg"] = "Erro ao adicionar comentário";
	echo json_encode($response);
	}
}
?>
