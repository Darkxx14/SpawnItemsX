# Spawn Items

Welcome to the most exceptional spawn items plugin out there! This plugin provides seamless functionality for spawning items in your Spigot server environment, making it a must-have for server administrators and players alike.

## Features

- **Effortless Configuration**: Customize your spawn items effortlessly with our intuitive YAML configuration.
- **Reloading**: Easily reload the configuration file on-the-fly with the `/spawnitems reload` command.
- **Give Command**: Grant players access to spawn items with the `/spawnitems give <player>` command.

## Commands

- `/spawnitems reload`: Reload the configuration file.
- `/spawnitems give <player>`: Give a player spawn items.

## Configuration

```yaml
# Hex and gradient support:
# - For hex codes, use the format: <SOLID:FBB846>&lɴᴀᴠɪɢᴀᴛɪᴏɴ
# - For gradients, use the format: <GRADIENT:2C08BA>&lɴᴀᴠɪɢᴀᴛɪᴏɴ</GRADIENT:028A97>

# Ensure commands don't include "/", for example:
# - Incorrect: right-click-command: /editkits
# - Correct: right-click-command: editkits

items:
  kitsel:
    item: DIAMOND_SWORD
    name: <SOLID:FBB846>&lɴᴀᴠɪɢᴀᴛɪᴏɴ
    slot: 0
    hide-attributes: true
    right-click-command: kitselector
  stats:
    item: PAPER
    name: <SOLID:FBB846>&lsᴛᴀᴛs
    slot: 1
    hide-attributes: true
    right-click-command: stats
  lb:
    item: NETHER_STAR
    name: <SOLID:FBB846>&lʟᴇᴀᴅᴇʀʙᴏᴀʀᴅs
    slot: 4
    hide-attributes: true
    right-click-command: leaderboards
  editkits:
    item: WRITABLE_BOOK
    name: <SOLID:FBB846>&lᴋɪᴛ ᴇᴅɪᴛᴏʀ
    slot: 7
    hide-attributes: true
    right-click-command: editkits
  settings:
    item: COMPASS
    name: <SOLID:FBB846>&lsᴇᴛᴛɪɴɢs
    slot: 8
    hide-attributes: true
    right-click-command: settings
```

## Compatibility

To maximize the functionality of this plugin, it seamlessly integrates with WorldGuard and Skript. This ensures that your spawn items are not only powerful but also adhere to the rules and restrictions you've set for your server environment.

```java
on death of a player:
    execute console command "spawnitems give %victim%"
on join:
    execute console command "spawnitems give %player%"
on region enter:
  if region is "hub" parsed as region:
    if player's world is "hub":
    execute console command "spawnitem give %player%"
```

A Xyris Plugin
