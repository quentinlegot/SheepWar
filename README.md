# SheepWar ![Java CI with Maven](https://github.com/SexiestCHiba/SheepWar/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master&event=push)

A plugin inspired by Epicube server (RIP).
Plugin do no work, it's currently WIP and untested


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

- /sw arena create <name>
  - Permission: sheepwar.arena.create
  - Description: Create a new arena named <name> if it not exist

- /sw arena remove <name>
  - Permission: sheepwar.arena.remove
  - Description: remove the arena <name> if it exist
  
- /sw arena list
  - Permission: sheepwar.arena.list
  - Description: List every arena
  
- /sw arena info <name>
  - Permission: sheepwar.arena.list
  - Description: display detailled information of arena <name>
  
- /sw arena schem - /sw admin schematic
  - Permission: sheepwar.arena.schem
  - Description: Save player worldedit selection of the arena as a .schem file to regenerate it after each game
 
- /sw arena state <state> -- NOT IMPLEMENTED
  - Permission: sheepwar.arena.state
  - Description: Force changing the state of an arena, <state> can only be WAITING or MAINTENANCE, game will pass through LOADING before
  
- /sw arena spawn -- NOT IMPLEMENTED
  - Permission: sheepwar.arena.spawn
  
- /sw arena help [page] - /sw admin -- WIP
  - Permission: sheepwar.arena.help
  - Description: Show help
 
