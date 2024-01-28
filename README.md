# Hide Nearby Players plugin
Minecraft plugin for 1.20.x that automatically hides players from each other when they are closer than a set distance.  
Mainly useful for parkour races when you want to be able to see other players at a distance without them blocking your view.

config.yml contains default-hide-distance which is the distance that is set before a player customizes it.  
Customized values are stored under the hide-distances-section attached to player uuid:s.  
Data is saved when the server is shutdown with the stop-command, or the plugin is disabled.

### Commands
#### /togglehide:  
Toggles the hide feature for the player that runs the command, available to all players on the server. (disables when leaving server)  

#### /hidedistance decimalValue:  
Changes the distance at which players are hidden, stored in config.yml-file.  
Every player can change their personal distance.
Can be any decimal value between 0 and 8.

#### /hideVerticalDistance decimalValue:
Changes the vertical hide distance. This is the same for every player.  

#### /hideTickDelay integerValue:
Changes the tick-delay between each check for distance between players. Only updated at restart.  

#### /hideStandardDistance decimalValue:
Changes the standard hide distance that is used when a player hasn't specified their own.  
