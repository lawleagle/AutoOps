How it works
--
This plugin will sync "op" with a mongo database.
If a player is autop (is in the autoOps collection in the mongo database), when he logs in, he will be given op automatically
If a player is not autoop, when he logs in he will be revoked op automatically.

How to use
--
/autoop add *player* <br>
Adds player to AutoOps (in mongodb defined in config)

/autoop remove *player* <br>
Removes player from AutoOps (in mongodb defined in config)