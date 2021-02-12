<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response = array("error"=>FALSE);
$flag=0;

if (empty($_POST["Classification"])){
        $flag=1;
        $response["error"] = TRUE;
        $response["error_msg"] = "Insira a classificação";
        echo json_encode($response);
} elseif (strlen($_POST["Classification"])!=3 || $_POST["Classification"][1]!="." || ($_POST["Classification"][0]=="5" && $_POST["Classification"][2]!=0) || $_POST["Classification"][0]<1 || $_POST["Classification"][0]>5) {
        $flag=1;
        $response["error"] = TRUE;
        $response["error_msg"] = "A classificação deve ser do tipo: 1.0 a 5.0";
        echo json_encode($response);
} elseif (empty($_POST["commentary"])) {
        $flag=1;
        $response["error"] = TRUE;
        $response["error_msg"] = "Insira a sua crítica";
        echo json_encode($response);
} elseif (strlen($_POST["commentary"])>200){
        $flag=1;
        $response["error"] = TRUE;
        $response["error_msg"] = "Excedeu os 200 caracteres. Diminua a sua crítica";
        echo json_encode($response);
}

if($flag==0){
        date_default_timezone_get("Europe/London");
        $data = date("Y-m-d H:i:s");
        $date = date("Y-m-d H:i:s", strtotime($data . " -1 hours"));
        $commentary=$db->storeCritic($_POST["idFoodPlace"],$_POST["Classification"],$date,$_POST["commentary"]);
        if($commentary){
                 $response["error"] = FALSE;
                 $response["error_msg"] = "Crítica foi adicionada e vai ser revista";
                 echo json_encode($response);
        }else {
        $response["error"] = TRUE;
        $response["error_msg"] = "Erro ao adicionar crítica";
        echo json_encode($response);
        }
}
?>
