<?php
session_start();
include 'connect.php';

$column = array();
$query = "SELECT * FROM users " or die ('Error: '.mysql_error ());
$result=mysql_query($query);

$num=mysql_num_rows($result);
		
//if($num != 0){
	
while($row=mysql_fetch_array($result)){
  $column[] = $row['username'];
}


$results = array(
    "result"   => "success",
    "username" => $num,
    "projects" => "some other value"
);

echo json_encode($column);

?>