## 1. Basic Linux Commands
    ls – Lists files and directories.

        ls -l: Lists with details (permissions, size, owner, etc.)
        ls -a: Shows hidden files.
        
    cd – Change directory.

        cd /path/to/directory: Navigate to a specific directory.
        cd ~: Goes to the home directory.
        cd ..: Goes up one directory level.
    
    pwd – Print the current working directory.

    mkdir – Create a directory.

    mkdir directory_name: Creates a new directory.
    rm – Remove files or directories.

        rm file_name: Deletes a file.
        rm -r directory_name: Recursively deletes a directory.

    cp – Copy files or directories.

        cp source destination: Copies a file.
        cp -r source_dir destination_dir: Recursively copy directories.
    
    mv – Move or rename files or directories.

        mv old_name new_name: Rename a file or directory.
        mv file_name destination_path: Move a file.
    
    touch – Create an empty file or update the timestamp of an existing file.

### 2. File Permissions
Linux file permissions determine who can read, write, or execute a file.

    r: Read permission (view the file).
    w: Write permission (modify the file).
    x: Execute permission (run the file as a program).

chmod – Change file permissions.

    chmod +x file_name: Adds execute permission.
    chmod 755 file_name: Sets permissions to rwxr-xr-x.

chown – Change file owner.

    chown user:group file_name: Changes ownership.
    
ls -l – Lists files and their permissions.
    Example: -rwxr-xr-x 1 user group 4096 Mar 1 12:34 file_name

Understanding Permission Codes:

    rwx is a combination of numbers:
    r = 4, w = 2, x = 1.
    Permissions are expressed as a 3-digit number: e.g., 755 means rwx for owner, r-x for group, and r-x for others.


### 3. File Manipulation
cat – Display file content.

cat file_name: Shows file content.
less – View large files interactively.

less file_name: Allows scrolling and searching in a file.
head – Show the first few lines of a file.

head file_name: Displays the first 10 lines by default.
tail – Show the last few lines of a file.

tail file_name: Displays the last 10 lines by default.
tail -f file_name: Continuously monitor a file for changes.
find – Search for files and directories.

find /path -name 'file_name': Searches for files with the given name.
grep – Search for patterns in files.

grep 'pattern' file_name: Finds matching patterns in a file.


### 4. Text Editors
nano – Easy-to-use command-line text editor.

nano file_name: Open file in nano editor.
Ctrl + X: Exit.
Ctrl + O: Save.
vim – Advanced text editor with powerful features.

vim file_name: Open file in vim.
i: Enter insert mode.
Esc: Exit insert mode.
:w: Save.
:q: Quit.
:wq: Save and quit.


### 5. Redirection and Pipes
Redirection:
>: Redirect output to a file (overwrites existing content).
command > file_name: Redirect output to a file.
>>: Append output to a file.
command >> file_name: Append to a file.
Pipes (|):
command1 | command2: Pass output from command1 as input to command2.
Example: ls | grep 'pattern': List files and filter with grep.


### 6. System Monitoring and Processes
ps – Display current processes.

ps aux: Shows all running processes.
ps -ef: Shows detailed process information.
top – Real-time system monitoring.

top: Displays running processes and system resource usage.
htop – Enhanced version of top (may need installation).

htop: Interactive process viewer.
kill – Terminate processes by PID.

kill PID: Sends a signal to terminate the process.
killall – Kill processes by name.

killall process_name: Kills all instances of the named process.

### 7. Networking and SSH
ifconfig – Display network configuration.

ifconfig: Shows network interfaces.
ping – Test network connectivity.

ping domain_or_ip: Sends ICMP echo request to a host.
netstat – Network statistics and connections.

netstat -tuln: Displays active listening ports.
ssh – Secure Shell (Remote login).

ssh user@hostname_or_ip: Log into a remote system securely.
Example: ssh root@192.168.1.10.
scp – Secure copy over SSH.

scp file_name user@hostname:/path/to/destination: Copies a file to a remote server.
curl – Transfer data from or to a server.

curl https://example.com: Fetches the content of a URL.

## 8. Shell Scripting Basics
Shell Script – A file containing a series of commands to be executed by the shell.

Example:

    #!/bin/bash
    echo "Hello, World!"
    
Make scripts executable:

    chmod +x script.sh: Make a shell script executable.

Variables:

    Declare variables: variable_name=value.
    Access variables: $variable_name.
    Example: name="John"; echo $name.

Control Structures:

If-else:

    if [ condition ]; then
    # commands
    else
    # commands
    fi

Loops:
for loop:   
    for i in {1..5}; do
    echo $i
    done

while loop:
    while [ condition ]; do
    # commands
    done


9. Package Management and Services
APT (Debian/Ubuntu):

sudo apt update: Update package lists.
sudo apt upgrade: Upgrade installed packages.
sudo apt install package_name: Install a package.
sudo apt remove package_name: Remove a package.
YUM (CentOS/RHEL):

sudo yum update: Update package lists and upgrade installed packages.
sudo yum install package_name: Install a package.
sudo yum remove package_name: Remove a package.
Systemd – Managing services.

systemctl start service_name: Start a service.
systemctl stop service_name: Stop a service.
systemctl enable service_name: Enable service to start on boot.
systemctl status service_name: Check service status.

