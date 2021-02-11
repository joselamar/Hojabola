<?php
session_start();
include('verifica_login.php');
require_once 'db_function.php';
$db = new DB_Functions();

$result= $db->getParkingCommentsWebsite();
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Rever Comentários dos Estacionamentos</title>
    </head>
    <body>
        <table border="2"  class="content-table">
                <tr>
		            <th></th>
                    <th>Nome do Estacionamento</th>
                    <th>Data</th>
                    <th>Comentário</th>
                    <th>Número de Lugares Disponiveís</th>
                </tr>
                <?php  WHILE($row= $result->fetch_assoc() ){ ?>
                <tr>
                    <td style="text-align:center;"><input form="myForm" type="checkbox" name="idReviews[]" value=<?php echo $row['idPReviews']?>  />&nbsp;</td>
                    <td><?php echo $db->getParkingName($row['Parking_idParking']); ?></td>
                    <td><?php echo $row['pDate']; ?></td>
                    <td><?php echo $row['pCommentary']; ?></td>
                    <td><?php echo $row['pPlacesAvailable']; ?></td>
                </tr>
            <?php }  ?>
         </table>
                <form action="reviewParkingComments.php"  method="post" id="myForm" onsubmit="return confirm('Deseja aceitar/apagar o(s) comentário(s) do(s) estacionamento(s)?');"> 
                <p><input type="submit" name="isAccepted" value="Aceitar"></p>
                <p><input type="submit" name="isAccepted" value="Eliminar"></p>
                </form>
        </body>
    </body>
</html>


