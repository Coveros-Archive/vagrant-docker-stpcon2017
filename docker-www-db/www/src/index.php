<html>
	<head>
		<title>Hello, <?= getenv('NAME') ?>!</title>
		<style>
			table { width: 25%; }
			table,th,td { border: 1px solid black; border-collapse: collapse; }
			th,td { padding: 5px; }
		</style>
	</head>

	<body>
		<h1>Hello, <?= getenv('NAME') ?>!</h1>

		<table>
			<thead>
				<tr>
					<th>State</th>
					<th>Abbreviation</th>
				</tr>
			</thead>
			
			<tbody>
				<?php
					$db_host = getenv('DB_HOST');
					$db_name = getenv('DB_NAME');
					$db_user = getenv('DB_USER');
					// Never, ever put a clear text password into the source
					$db_pass = getenv('DB_PASS');
					
					// Create connection
					$conn = new mysqli($db_host, $db_user, $db_pass, $db_name);
					
					// Check connection
					if ($conn->connect_error) {
						die("Connection failed: " . $conn->connect_error);
					}

					$sql = "SELECT state,abbreviation FROM states";
					$result = $conn->query($sql);

					if ($result->num_rows > 0) {
						// output data of each row
						while ($row = $result->fetch_assoc()) {
							echo "<tr><td>" . $row["state"] . "</td><td>" . $row["abbreviation"] . "</td></tr>";
						}
					} else {
						echo "0 results";
					}

					$conn->close();
				?>
			</tbody>
		</table>

		<p id="installed">
			This web server and template were
			installed to <?= $_SERVER['SERVER_NAME'] ?>
			with IP address <?= $_SERVER['SERVER_ADDR'] ?>.
		</p>
	</body>
</html>
