<?php

class DB_Functions{
	private $conn;

	function __construct(){
		require_once 'db_connect.php';
		$db = new DB_Connect();
		$this->conn = $db->connect();
	}

	function __destruct(){
		// TODO: Implement __destruct() method.
	}
	
	public function storeCritic($idFoodPlace,$classification,$date,$commentary){
	$stmt=$this->conn->prepare("INSERT INTO `fReviews` (`idReviews`, `FoodPlaces_idFoodPlaces`, `rClassification`, `rCommentary`, `rDate`) VALUES (NULL,?,?,?,?)");
	$stmt->bind_param("ssss",$idFoodPlace,$classification,$commentary,$date);
	return $stmt->execute();
	}		

	public function getFoodPlaceComments($idFoodPlace){
        $stmt=$this->conn->prepare("SELECT * FROM fReviews WHERE FoodPlaces_idFoodPlaces=? AND isChecked=1");
        $stmt->bind_param("s",$idFoodPlace);
        $stmt->execute();
        $queryResult = $stmt->get_result()->fetch_all();
        return $queryResult;
        }

	public function getFoodPlaces($idStadium){
        $stmt=$this->conn->prepare("SELECT * FROM FoodPlaces WHERE Stadium_idStadium=?");
        $stmt->bind_param("s",$idStadium);
        $stmt->execute();
        $queryResult = $stmt->get_result()->fetch_all();
        return $queryResult;
        }

	public function getComments($idParking){
	$stmt=$this->conn->prepare("SELECT * FROM pReviews WHERE Parking_idParking=? AND isChecked=1");
        $stmt->bind_param("s",$idParking);
        $stmt->execute();
        $queryResult = $stmt->get_result()->fetch_all();
        return $queryResult;
	}

	public function storeCommentary($idParking,$aSpots,$date,$commentary){
	$stmt=$this->conn->prepare("INSERT INTO goesToParking (`idGTP`, `User_idUser`, `Parking_idParking`) VALUES (NULL, '1', ?)");
	$stmt->bind_param("s",$idParking);
	$stmt->execute();
	$stmt2 = $this->conn->prepare("SELECT MAX(idGTP) FROM `goesToParking`");
        $stmt2->execute();
        $stmt2Result = $stmt2->get_result()->fetch_row();
        $s2r=$stmt2Result[0];
	$stmt1=$this->conn->prepare("INSERT INTO `pReviews` (`idPReviews`, `pPlacesAvailable`, `pCommentary`, `pDate`, `goesToParking_idGTP`, `Parking_idParking`) VALUES (NULL,?,?,?,?,?)");
	$stmt1->bind_param("sssss",$aSpots,$commentary,$date,$s2r,$idParking);
	return $stmt1->execute();
	}
	
	public function getStandings(){
	$stmt = $this->conn->prepare("SELECT * FROM Standings ORDER BY ABS(position)");
        $stmt->execute(); 
        $queryResult = $stmt->get_result()->fetch_all();               
	return $queryResult;
	}

	public function getParkings($idStadium){
	$stmt=$this->conn->prepare("SELECT * FROM Parking WHERE Stadium_idStadium=? AND isChecked=1");
	$stmt->bind_param("s",$idStadium);
	$stmt->execute();
	$queryResult = $stmt->get_result()->fetch_all();
        return $queryResult;
	}


		
	public function updateStandings($club,$points,$gamesPlayed,$goals,$position){
	$stmt=$this->conn->prepare("UPDATE `Standings` SET `Club_idClub` = ?, `Points` = ?, `gamesPlayed` = ?, `goals` = ?, `position` = ? WHERE `Standings`.`idStandings` = ?"); 
	$clubID=$this->getClubID($club);
	$stmt->bind_param("ssssss",$clubID,$points,$gamesPlayed,$goals,$position,$clubID);
	$stmt->execute();
	$stmt->close();
	}
		
	public function storeParking($parkingName,$parkingAdress,$parkingSpots,$parkingASpots,$parkingStadium,$parkingCoordinates,$parkingISpayed,$path){
	$stmt=$this->conn->prepare("INSERT INTO Parking(pName,pAdress,pSpots,pAvailable,Stadium_idStadium,pCoordinates,isPayed,path) VALUES (?,?,?,?,?,?,?,?)");
	$stmt->bind_param("ssssssss",$parkingName,$parkingAdress,$parkingSpots,$parkingASpots,$parkingStadium,$parkingCoordinates,$parkingISpayed,$path);
	if($stmt->execute()){
		$stmt->close();
		return true;
	} else {
		$stmt->close();
		return false;
	}
	}

	public function storeGames($score,$competition,$date,$round,$homeTeam,$awayTeam){
	$homeTeamID = $this->getClubID($homeTeam);
	$awayTeamID = $this->getClubID($awayTeam);
	$stmt1 = $this->conn->prepare("INSERT INTO Game (gScore,Competition_idCompetition,gDate,gRound,Club_idClub,Club_idClub1,Stadium_idStadium) VALUES (?,?,?,?,?,?,?)"); //para fazer a ligação$
        $stmt1->bind_param("sssssss",$score,$competition,$date,$round,$homeTeamID,$awayTeamID,$homeTeamID);
	$stmt1->execute();
	}

	public function getClubID($team){
	if (strpos($team,"Aves") !== false){ return 1; }
	if (strpos($team,"Belenenses") !== false){ return 2; }
	if (strpos($team,"Benfica") !== false){ return 3; }
	if (strpos($team,"Boavista") !== false){ return 4; }
	if (strpos($team,"Braga") !== false){ return 5; }
	if (strpos($team,"Famalicão") !== false){ return 6; }
	if (strpos($team,"Ferreira") !== false){ return 7; }
	if (strpos($team,"Vicente") !== false){ return 8; }
	if (strpos($team,"Marítimo") !== false){ return 9; }
	if (strpos($team,"Moreirense") !== false){ return 10; }
	if (strpos($team,"Portimonense") !== false){ return 11; }
	if (strpos($team,"Porto") !== false){ return 12; }
	if (strpos($team,"Rio Ave") !== false){return 13; }
	if (strpos($team,"Santa Clara") !== false){ return 14; }
	if (strpos($team,"Sporting") !== false){ return 15; }
	if (strpos($team,"Tondela") !== false){ return 16; }
	if (strpos($team,"Vitória FC") !== false){ return 17; }
	if (strpos($team,"Vitória SC") !== false){ return 18; }
	}

	public function storeUser($name,$email,$password,$club){
		$hash = $this->hashSSHA($password);
		$repPassword = $hash["encrypted"];
		$salt = $hash["salt"];

		$query = $this->conn->prepare("SELECT idClub FROM `Club` WHERE cName='$club'"); //para saber o id do clube que foi selecionado aquando o registo
		$query->execute();
		$queryResult = $query->get_result()->fetch_row();
		$qr= $queryResult[0]; //prq o result da query não é uma string
		$stmt1 = $this->conn->prepare("INSERT INTO has(idHas,Club_idClub) VALUES (NULL,?)"); //para fazer a ligação entre o clube e o user
		$stmt1->bind_param("s",$qr);
		$stmt1->execute();
		$stmt2 = $this->conn->prepare("SELECT MAX(idHas) FROM `has`");
		$stmt2->execute();
		$stmt2Result = $stmt2->get_result()->fetch_row();
		$s2r=$stmt2Result[0];
		$stmt = $this->conn->prepare("INSERT INTO User(idUser,uName,uEmail,uRep_Password,uSalt,has_idHas) VALUES (NULL,?,?,?,?,?)");
		$stmt->bind_param("sssss",$name,$email,$repPassword,$salt,$s2r);
		$result = $stmt->execute();
		$query->close();
		$stmt1->close();
		$stmt2->close();
		$stmt->close();

		if($result){
			$stmt = $this->conn->prepare("SELECT * FROM User WHERE uEmail = ?");
			$stmt->bind_param("s",$email);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
			return $user;
		} else{
			return false;
		} 
		$stmt->close();

	}

	public function getUserClub($email){
		$query = $this->conn->prepare("SELECT has_idHas FROM `User` WHERE uEmail=?");
		$query->bind_param("s",$email);
		$query->execute(); 
		$queryResult = $query->get_result()->fetch_row();
		$qr= $queryResult[0];
		$query1 = $this->conn->prepare("SELECT Club_idClub FROM `has` WHERE idHas=?");
		$query1->bind_param("s",$qr);
		$query1->execute();
		$queryResult1 = $query1->get_result()->fetch_row();
		$qr1= $queryResult1[0];
		$query2 = $this->conn->prepare("SELECT cName FROM `Club` WHERE idClub=?");
		$query2->bind_param("s",$qr1);
		$result = $query2->execute();
		if($result){
		$output = array();
		$queryResult2 = $query2->get_result()->fetch_row();
		$qr2= $queryResult2[0];
		$output["club"]=$qr2;
		return $output;
		} else {
			return false;
		}
		$query->close();
		$query1->close();
		$query2->close();
	}

	public function getUserByNameAndPassword($name,$password){
		$stmt = $this->conn->prepare("SELECT * FROM User WHERE uName = ?");
		$stmt->bind_param("s",$name);

		if($stmt->execute()){
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();

			//verifying user password
			$salt = $user['uSalt'];
			$repPassword = $user['uRep_Password'];
			$hash = $this->checkhashSSHA($salt,$password);
			//check for password equality
			if($repPassword == $hash)
				return $user;
		} else {
			return NULL;
			$stmt->close();
		}
	}

	//para não repetir email 
	public function doesEmailExist($email){
		$stmt = $this->conn->prepare("SELECT uEmail from User WHERE uEmail = ?");
		$stmt->bind_param("s",$email);
		$stmt->execute();
		$stmt->store_result();

		if($stmt->num_rows > 0){
			$stmt->close();
			return true;
		} else {
			$stmt->close();
			return false;
		}
	}

	public function getGames($club){
		$stmt = $this->conn->prepare("SELECT * FROM `Game`");
		$stmt->execute(); 
		$queryResult = $stmt->get_result()->fetch_all();
		return $queryResult;
	}
	
	public function getStadiums($club){
		$this->conn->set_charset("utf8");
		$query = $this->conn->prepare("SELECT * FROM `Stadium`");
		$query->execute();
		$queryResult=$query->get_result()->fetch_all();
		return $queryResult;
	}	

	public function getCompetition($idCompetition){
                $query = $this->conn->prepare("SELECT coName FROM Competition WHERE idCompetition=?");
                $query->bind_param("s",$idCompetition);
                $query->execute(); 
                $result=$query->get_result()->fetch_row();
                $qr=$result[0];
                return $qr;
        }

	
	public function getStadiumCoordinates($idStadium){
	 	$query = $this->conn->prepare("SELECT sCoordinates  FROM `Stadium` WHERE `idStadium`=?");
                $query->bind_param("s",$idStadium);
                $query->execute(); 
                $result=$query->get_result()->fetch_row();
                $qr=$result[0];
		return $qr;
	}
		
	public function getClubName($idClub){
	$query2 = $this->conn->prepare("SELECT cName FROM `Club` WHERE idClub=?");
                $query2->bind_param("s",$idClub);
                $result = $query2->execute();
                $queryResult2 = $query2->get_result()->fetch_row();
                $qr2= $queryResult2[0];
	return $qr2;
	}

	//para não repetir username
	public function doesNameExist($name){
		$stmt = $this->conn->prepare("SELECT uName from User WHERE uName = ?");
		$stmt->bind_param("s",$name);
		$stmt->execute();
		$stmt->store_result();

		if($stmt->num_rows > 0){
			$stmt->close();
			return true;
		} else {
			$stmt->close();
			return false;
		}
	}

	    public function hashSSHA($password){
                $salt = openssl_random_pseudo_bytes(10);
                $iterations = 1000;
                $rep  = hash_pbkdf2("sha256", $password, $salt, $iterations, 0);
                $hash = array("salt"=>$salt,"encrypted"=>$rep);

                return $hash;
        }

        public function checkhashSSHA($salt,$password){
                $iterations = 1000;
                $hash = hash_pbkdf2("sha256", $password, $salt, $iterations, 0);
                return $hash;
        }
}

?>
