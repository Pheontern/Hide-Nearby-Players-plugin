# Hide Nearby Players plugin
Minecraft plugin for 1.20.x that automatically hides players from each other when they are closer than a set distance.  
Mainly useful for parkour races when you want to be able to see other players at a distance without them blocking your view.

config.yml contains default-hide-distance which is the distance that is set before a player customizes it.  
Customized values are stored under the hide-distances-section attached to player uuid:s.  
Data is saved when the server is shutdown with the stop-command, or the plugin is disabled.

### Commands
#### /togglehide:  
Toggles the hide feature for the player that runs the command, available to all players on the server. (disables when leaving server)  

#### /hidedistance 2.5:  
Changes the distance at which players are hidden, stored in config.yml-file.  
Every player can change their personal distance.
Can be any decimal value between 0 and 8.
