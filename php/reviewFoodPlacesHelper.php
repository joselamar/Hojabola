<?php
session_start();
include('verifica_login.php');
require_once 'db_function.php';
$db = new DB_Functions();

$result= $db->getFoodPlaceCommentsWebsite();
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Rever Críticas da Restauração</title>
    </head>
    <body>
        <table border="2"  class="content-table">
                <tr>
                    <th></th>
                    <th>Nome do Restaurante</th>
                    <th>Data</th>
                    <th>Classificação</th>
                    <th>Comentário</th>
                </tr>
                <?php  WHILE($row= $result->fetch_assoc() ){ ?>
                <tr>
                    <td style="text-align:center;"><input form="myForm" type="checkbox" name="idReviews[]" value=<?php echo $row['idReviews']?>  />&nbsp;</td>
                    <td><?php echo $db->getFoodPlaceName($row['FoodPlaces_idFoodPlaces']); ?></td>
                    <td><?php echo $row['rDate']; ?></td>
                    <td><?php echo $row['rClassification']; ?></td>
                    <td><?php echo $row['rCommentary']; ?></td>
                </tr>
            <?php }  ?>
         </table>
                <form action="reviewFoodPlace.php"  method="post" id="myForm" onsubmit="return confirm('Deseja aceitar/apagar a(s) crítica(s) do(s) estabelecimento(s)?');"> 
                <p><input type="submit" name="isAccepted" value="Aceitar"></p>
                <p><input type="submit" name="isAccepted" value="Eliminar"></p>
                </form>
        </body>
    </body>
</html>

