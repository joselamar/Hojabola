<!DOCTYPE html>
<?php
session_start();
include('verifica_login.php');
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Confiança</title>
    </head>
    <body>
    <h2> Editar Nível de Confiança </h2>
	 <form action="editUserTrust.php" method="post"onsubmit="return confirm('Deseja mesmo editar o nível de confiança?');">
            Nome do Utilizador: <input type="text" name="username" >
		<p></p>
		<div>Selecione o novo nível de confiança: <select id="cbx_uTrust" name="trust">
		<option value="0">0</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
		</select>
		</div>
		<p></p>
            <input type="submit" value="Editar" name="Editar">
            <br>
        </form> 
        </div>
       
  
    </body>
</html>
