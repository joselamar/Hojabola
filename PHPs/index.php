<?php
session_start();
?>

<!DOCTYPE html>
<html>
    
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <link rel="stylesheet" href="css/bulma.min.css" />
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
    <section class="hero is-success is-fullheight">
        <div class="hero-body">
            <div class="container has-text-centered">
                <div class="column is-4 is-offset-4">
                    <h3 class="title has-text-grey">Login Hojábola BackOffice</h3>
                    <?php
                    if(isset($_SESSION['not_loged'])):
                    ?>
                    <div class="notification is-danger">
                      <p>ERROR: User or password invalid.</p>
                    </div>
                    <?php
                    endif;
                    unset($_SESSION['not_loged']);
                    ?>
                    <div class="box">
                        <form action="login.php" method="POST">
                            <div class="field">
                                <div class="control">
                                    <input name="username" name="text" class="input is-large" placeholder="Username" autofocus="">
                                </div>
                            </div>

                            <div class="field">
                                <div class="control">
                                    <input name="password" class="input is-large" type="password" placeholder="Password">
                                </div>
                            </div>
                            <button type="submit" class="button is-block is-link is-large is-fullwidth">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>

</html>
