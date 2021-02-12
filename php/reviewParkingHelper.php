<?php
session_start();
include('verifica_login.php');
require_once 'db_function.php';
$db = new DB_Functions();

$result= $db->getParkingWebsite();
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Rever Estacionamentos</title>
        <link rel="stylesheet" href="css/StyleWebService.css" type="text/css">
    </head>
    <body>
    	<table border="2"  class="content-table">
                <tr>
		    <th></th>
                    <th>Nome do Estacionamento</th>
                    <th>Morada do Estacionamento</th>
                    <th>Número de Lugares</th>
                    <th>Número de Lugares Disponiveís</th>
                    <th>Estádio</th>
		    <th>Coordenadas</th>
                    <th>É pago</th>
                    <th>Imagem</th>
                </tr>
                <?php  WHILE($row= $result->fetch_assoc() ){ ?>
                <tr>
		    <td style="text-align:center;"><input form="myForm" type="checkbox" name="idParking[]" value=<?php echo $row['idParking']?>  />&nbsp;</td>
                    <td><?php echo $row['pName']; ?></td>
                    <td><?php echo $row['pAdress']; ?></td>
                    <td><?php echo $row['pSpots']; ?></td>
                    <td><?php echo $row['pAvailable']; ?></td>
                    <td><?php echo $db->getStadiumName($row['Stadium_idStadium']); ?></td>
                    <td><?php echo $row['pCoordinates']; ?></td>
                    <td><?php if($row['isPayed']=="0") echo "Grátis"; else echo "Pago";  ?></td>
		    <td><?php if($row['path']!="NULL") echo "<img src='projectapi/{$row['path']}' width='250'"   ?></td>
                </tr>
            <?php }  ?>
         </table>
		<form action="reviewParking.php"  method="post" id="myForm" onsubmit="return confirm('Deseja aceitar/apagar o(s) estacionamento(s)?');"> 
		<p><input type="submit" name="isAccepted" value="Aceitar"></p>
		<p><input type="submit" name="isNotAccepted" value="Eliminar"></p>
		</form>
	</body>
    </body>
</html>

