<?php
require_once 'db_function.php';
$db = new DB_Functions();
//json response
$response = array("error"=>FALSE);
$flag=0;

if (empty($_POST["parkingName"])){
	$flag=1;
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira o nome do Estacionamento";
	echo json_encode($response);
} elseif (empty($_POST["parkingAdress"])) {
	$flag=1;
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira a morada do Estacionamento";
	echo json_encode($response);
} elseif (empty($_POST["parkingSpots"])) {
	$flag=1;
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira uma estimativa de lugares";
	echo json_encode($response);
} elseif (empty($_POST["parkingASpots"])) {
	$flag=1;
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira uma estimativa de lugares disponiveís";
	echo json_encode($response);
} elseif (empty($_POST["parkingStadium"])) {
	$flag=1;
	$response["error"] = TRUE;
	$response["error_msg"] = "Insira o estádio referente ao Estacionamento";
	echo json_encode($response);
} elseif (empty($_POST["parkingCoordinates"])) {
	$flag=1;
	$response["error"] = TRUE;
	$response["error_msg"] = "Ative a localização para dar as coordenadas do Estacionamento";
	echo json_encode($response);
} elseif (is_null($_POST["parkingISpayed"])){
	$flag=1;
	$response["error"] = TRUE;
	$response["error_msg"] = "Indique se o Estacionamento é pago ou grátis";
	echo json_encode($response);
}
if($flag==0){ 
if(!empty($_POST["encoded_string"])){
	$decoded_string = base64_decode($_POST["encoded_string"]);
	$path = 'images/'.$_POST["parkingName"];
	$file = fopen($path, 'wb');

	$is_written = fwrite($file, $decoded_string);
	fclose($file);

	if($is_written > 0 ){
		 $parking= $db->storeParking($_POST["parkingName"],$_POST["parkingAdress"],$_POST["parkingSpots"],$_POST["parkingASpots"],$_POST["parkingStadium"],$_POST["parkingCoordinates"],$_POST["parkingISpayed"],$path);
		 if ($parking) {
		 	$response["error"] = FALSE;
			$response["error_msg"] = "Estacionamento foi adicionado e vai ser revisto";
			echo json_encode($response);
		 } else {
			$response["error"] = TRUE;
			$response["error_msg"] = "Erro ao adicionar Estacionamento";
			echo json_encode($response);
		 }
	} else {
			$response["error"] = TRUE;
			$response["error_msg"] = "Erro ao adicionar Estacionamento";
			echo json_encode($response);
	}
} else {
	$parking= $db->storeParking($_POST["parkingName"],$_POST["parkingAdress"],$_POST["parkingSpots"],$_POST["parkingASpots"],$_POST["parkingStadium"],$_POST["parkingCoordinates"],$_POST["parkingISpayed"],"NULL");
		 if ($parking){
		 	$response["error"] = FALSE;
			$response["error_msg"] = "Estacionamento foi adicionado e vai ser revisto";
			echo json_encode($response);
		 } else {
			$response["error"] = TRUE;
			$response["error_msg"] = "Erro ao adicionar Estacionamento";
			echo json_encode($response);
		 } 
}
}

?>
