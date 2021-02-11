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
	
	public function acceptFoodPlaceComments($idReview){
        $stmt=$this->conn->prepare("UPDATE fReviews SET isChecked=1 WHERE idReviews=?");
        $stmt->bind_param("s",$idReview);
        $stmt->execute();
        $stmt->close();
        }
        
        public function deleteFoodPlaceComments($idReview){
        $stmt=$this->conn->prepare("DELETE FROM fReviews WHERE idReviews=?");
        $stmt->bind_param("s",$idReview);
        $stmt->execute();
        $stmt->close();
        }
	
	public function getFoodPlaceName($idFoodPlace){
	$stmt=$this->conn->prepare("SELECT fName FROM FoodPlaces WHERE idFoodPlaces=?");
        $stmt->bind_param("s",$idFoodPlace);
        $stmt->execute();
        $result=$stmt->get_result()->fetch_assoc();
        return $result['fName'];
	}	

	public function getFoodPlaceCommentsWebsite(){
	 $stmt=$this->conn->prepare("SELECT * FROM fReviews WHERE isChecked=0");
        $stmt->execute();
        return $stmt->get_result();
	}
	
	public function acceptParkingComments($idReview){
        $stmt=$this->conn->prepare("UPDATE pReviews SET isChecked=1 WHERE idPReviews=?");
        $stmt->bind_param("s",$idReview);
        $stmt->execute();
        $stmt->close();
        }
        
        public function deleteParkingComments($idReview){
        $stmt=$this->conn->prepare("DELETE FROM pReviews WHERE idPReviews=?");
        $stmt->bind_param("s",$idReview);
        $stmt->execute();
        $stmt->close();
        }
	
	public function getParkingName($idParking){
        $stmt=$this->conn->prepare("SELECT pName FROM Parking WHERE idParking=?");
        $stmt->bind_param("s",$idParking);
        $stmt->execute();
        $result=$stmt->get_result()->fetch_assoc();
        return $result['pName'];
        }

	public function getParkingCommentsWebsite(){
        $stmt=$this->conn->prepare("SELECT * FROM pReviews WHERE isChecked=0");
        $stmt->execute();
        return $stmt->get_result();
        }
	
	public function acceptParking($idParking){
	$stmt=$this->conn->prepare("UPDATE Parking SET isChecked=1 WHERE idParking=?");
	$stmt->bind_param("s",$idParking);
        $stmt->execute();
        $stmt->close();
	}
	
	public function deleteParking($idParking){
	$stmt=$this->conn->prepare("DELETE FROM Parking WHERE idParking=?");
	$stmt->bind_param("s",$idParking);
	$stmt->execute();
	$stmt->close();
	}
	
	public function getStadiumName($idStadium){
	$stmt=$this->conn->prepare("SELECT sName FROM Stadium WHERE idStadium=?");
	$stmt->bind_param("s",$idStadium);
	$stmt->execute();
	$result=$stmt->get_result()->fetch_assoc();
	return $result['sName'];
	}

	public function getParkingWebsite(){
        $stmt=$this->conn->prepare("SELECT * FROM Parking WHERE isChecked=0");
        $stmt->execute();
        return $stmt->get_result();
        }

	public function isAdmin($name,$password){
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
			if($repPassword == $hash){
				if ($user['isAdmin']==1){
					return true;
				}
			}
		} else {
			$stmt->close();
			return false;
		}
	}
	
	public function isUser($name){
                $stmt = $this->conn->prepare("SELECT * FROM User WHERE uName = ?");
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
	
	public function isAdminUser($name){
                $stmt = $this->conn->prepare("SELECT * FROM User WHERE uName = ? AND isAdmin=1");
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

	
	public function updateTrust($name,$trust){
		$stmt = $this->conn->prepare("UPDATE `User` SET `uTrust` = '$trust' WHERE uName ='$name'");
		if($stmt->execute()){
		$stmt->close();
		return true;
		}else {
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
