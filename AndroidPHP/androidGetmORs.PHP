<?php
session_start();
include 'connect.php';

	$username=$_POST['username'];
	$password=$_POST['pass'];


	$username=trim(str_replace(" ","",strtolower(strip_tags(stripslashes(mysql_real_escape_string($username))))));
	$password=trim(strip_tags(stripslashes(mysql_real_escape_string($password))));
	$password_hash=md5($password);

$status = 0;
if($username && $password){
		$query="SELECT * FROM users WHERE username='$username' AND password='$password_hash'" or die ('Error: '.mysql_error ());
		$result=mysql_query($query);
		$num=mysql_num_rows($result);
		
		if($num != 0){
	
			while($row=mysql_fetch_array($result)){
				$username=$row['username'];
				
			}

			//echo 'owner';
			$status = 1;
		}

}


$column = array();
if($status)
$query = "SELECT * FROM items WHERE owner='$username'" or die ('Error: '.mysql_error ());
$result=mysql_query($query);

//$num=mysql_num_rows($result);
		
//if($num != 0){
	
while($row=mysql_fetch_array($result)){
  $column[] = $row['mORs'];
}


$results = array(
    "result"   => "success",
    "username" => $num,
    "projects" => "some other value"
);

echo json_encode($column);

?>