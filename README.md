# SheepWar ![Java CI with Maven](https://github.com/SexiestCHiba/SheepWar/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master&event=push)

A plugin inspired by Epicube server (RIP).
Plugin do no work, it's currently WIP and untested


## Commands

/sheepwar or its alias /sw

### Players commands

- /sw join <arena_name>
  - Permission: sheepwar.join
  - Description: Join an Arena
 
- /sw leave
  - Permission: sheepwar.leave
  - Description: Leave an arena while game is in progress

- /sw list -- NOT IMPLEMENTED
  - Permission: sheepwar.list
  - Description: List all arena available to be played

# Admin commands

- /sw arena create <arena_name>
  - Permission: sheepwar.arena.create
  - Description: Create a new arena named <name> if it not exist

- /sw arena remove <arena_name>
  - Permission: sheepwar.arena.remove
  - Description: remove the arena <name> if it exist
  
- /sw arena list
  - Permission: sheepwar.arena.list
  - Description: List every arena
  
- /sw arena info <arena_name>
  - Permission: sheepwar.arena.list
  - Description: display detailled information of arena <name>
  
- /sw arena schem <arena_name> - /sw admin schematic <arena_name>
  - Permission: sheepwar.arena.schem
  - Description: Save player worldedit selection of the arena as a .schem file to regenerate it after each game
 
- /sw arena state <arena_name> <state>
  - Permission: sheepwar.arena.state
  - Description: Force changing the state of an arena, <state> can only be WAITING or MAINTENANCE, game will pass through LOADING before
  
- /sw arena spawn <arena_name> <add/remove/list> [team] [integer]
  - Permission: sheepwar.arena.spawn
  - Description: Add/edit/list/remove team spawns and lobby location
  
- /sw arena help [page] - /sw admin -- WIP
  - Permission: sheepwar.arena.help
  - Description: Show help
 
- /sw kits new -- NOT IMPLEMENTED

- /sw kits edit -- NOT IMPLEMENTED

- /sw kits list -- NOT IMPLEMENTED

- /sw kits remove -- NOT IMPLEMENTED
