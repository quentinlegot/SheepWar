# SheepWar

## Commands

/sheepwar or its alias /sw

### Players commands

- /sw join <arena>
  - Permission: sheepwar.join
  - Description: Join an Arena
 
- /sw leave
  - Permission: sheepwar.leave
  - Description: Leave an arena while game is in progress

- /sw list -- NOT IMPLEMENTED
  - Permission: sheepwar.list
  - Description: List all arena available to be played

# Admin commands

- /sw admin create <name>
  - Permission: sheepwar.admin.create
  - Description: Create a new arena named <name> if it not exist

- /sw admin remove <name>
  - Permission: sheepwar.admin.remove
  - Description: remove the arena <name> if it exist
  
- /sw admin list
  - Permission: sheepwar.admin.list
  - Description: List every arena
  
- /sw admin info <name>
  - Permission: sheepwar.admin.list
  - Description: display detailled information of arena <name>
  
- /sw admin schem - /sw admin schematic
  - Permission: sheepwar.admin.schem
  - Description: Save player worldedit selection of the arena as a .schem file to regenerate it after each game
 
- /sw admin state <state> -- NOT IMPLEMENTED
  - Permission: sheepwar.admin.state
  - Description: Force changing the state of an arena, <state> can only be WAITING or MAINTENANCE, game will pass through LOADING before
  
- /sw admin spawn -- NOT IMPLEMENTED
  - Permission: sheepwar.admin.spawn
  
- /sw admin help [page] - /sw admin -- WIP
  - Permission: sheepwar.admin.help
  - Description: Show help
 
