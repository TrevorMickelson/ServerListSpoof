# ServerListSpoof <img align="right" src="https://user-images.githubusercontent.com/70197204/156938000-aa53a96f-ebc9-4beb-8e74-520d444a09fe.png" height="200" width="225">
This plugin is not something I'd ever personally use, but it was requested of me by someone a while back.
Basically, this plugin displays inaccurate number data on serverlist sites, but in-game it doesn't.
Here's are example servers that do this. It's a common tactic to get more joins.

### Here's how they look on the server list website 
<img src="https://user-images.githubusercontent.com/70197204/156938373-628b2594-460c-4a16-9a65-c3c52d1a8b37.png" height="200" width="200">
![serverlistspoof-screenshot_1](https://user-images.githubusercontent.com/70197204/156938371-54133e97-6398-4da5-910b-19b083a18d5e.png)
![serverlistspoof-screenshot_2](https://user-images.githubusercontent.com/70197204/156938373-628b2594-460c-4a16-9a65-c3c52d1a8b37.png)

### Here's how they look in game
![serverlistspoof-screenshot_3](https://user-images.githubusercontent.com/70197204/156938385-691af40b-84e6-47b0-9541-dcf795737467.png)

### Important
This is a bungee plugin (not a spigot or paper plugin)

### Commands
- slsreload (permission configurable)

### Configuration
```yaml
Prefix: "&8[&4&lServerListSpoof&8] &c"
Permission: "serverlistspoof.reload"
ReportValueMax: 200
BungeeReporting: false

ServerLists:
  MCSL_&_MP:
    ProtocolVersion: 4
    MultiplyAmount: 2.5
  MinecraftServersOrg:
    ProtocolVersion: 109
    MultiplyAmount: 2.5
```

### How to use
When you first start, you'll want to enable "BungeeReporting"
Then, you'll need to ping your server from a specific website.
This will send the "ProtocolVersion" to you, for your config.

The setup is a bit annoying, but if you want to display higher
numbers on any website, this is the way to do it. 
