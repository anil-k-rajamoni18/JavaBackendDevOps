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

    - Three levels of permissions 
        - Owner (u) 
        - Group (g)
        - Other (o)

    Example: rwxr-x---
        Owner: rwx
        Group: r-x
        Other: ---

chmod – Change file permissions.
    chmod [OPTION] MODE FILE...
    - Permissions are represented as a three-digit number or symbolic characters (like r, w, x).
    - Numeric (Octal) Notation: Uses three digits representing the permissions for the owner, group, and others.
        r = 4, w = 2, x = 1
        Owner, group, and others have different values.
    - Symbolic Notation: Uses letters (r, w, x) and operators (+, -, =) to set permissions.

    Common Examples:
        chmod 744 file.txt : Grant rwx permissions to the owner (and only read to others):
        chmod 755 file.txt : Grant rwx permissions to the owner, and only read to group and others
        chmod u+x file.txt : Give execute permission to the owner
        chmod g-w file.txt : Remove write permission for the group
        chmod a+rwx file.txt : Set read, write, and execute permissions for everyone
        chmod -R 755 /home/user/docs : Change file permissions recursively




chown – Change file owner.
    - The chown command in Linux is used to change the owner and/or group of a file or directory. 
    - chown [OPTION] USER[:GROUP] FILE...
    
    Common Examples:
        chown john file.txt         : Change the owner of a file
        chown john:admin file.txt   : Change the owner and the group of a file
        chown -R john:admin /home/user/docs : Change the owner of a directory and all its contents (recursive)
        chown :admin file.txt       : Change group only



### 3. File Manipulation
    cat : Display file content.

        cat file_name: Shows file content.
    
    less : View large files interactively.
        less file_name: Allows scrolling and searching in a file.
    
    head : Show the first few lines of a file.
        head file_name: Displays the first 10 lines by default.
    
    tail : Show the last few lines of a file.

        tail file_name: Displays the last 10 lines by default.
        tail -f file_name: Continuously monitor a file for changes.
    
    find : Search for files and directories 
        find [path] [options] [expression]
        1. Find all files with a specific name:         find /home/user -name "file.txt"
        2. Find files by type (e.g., directories):      find /home/user -type d
        3. Find files with specific size::              find /home/user -size +100M
        4. Find and delete files:                       find /home/user -name "*.log" -delete

    grep : Search for patterns in files. 
        - It can be used to search for specific text in one or more files and print the matching lines.
        - grep [options] 'pattern' [file]
        1. Search for a pattern in a file:                          grep "hello" myfile.txt
        2. Search for a pattern in multiple files:                  grep "error" *.log
        3. Search recursively for a pattern in a directory:         grep -r "warning" /var/log/
        4. Ignore case while searching:                             grep -i "example" myfile.txt
        5. Count the number of matches:                             grep -c "error" *.log



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
        echo "Hello, world!" > output.txt
    
    >>: Append output to a file.
        command >> file_name: Append to a file.
        echo "This is another line." >> output.txt

    Input Redirection (<): The < operator is used to take input for a command from a file, rather than from the terminal.
        sort < input.txt

    Error Redirection (2>): The 2> operator is used to redirect error output (stderr) to a file.
        ls non_existent_directory 2> error.txt

    Redirecting both stdout and stderr (&>): You can redirect both the standard output and error output to a file using &>.
        ls non_existent_directory &> output.txt


Pipes (|):
    command1 | command2: Pass output from command1 as input to command2.
     Example: 
        ls | grep 'pattern': List files and filter with grep.
        cat file.txt | grep "pattern"
        cat file.txt | grep "pattern" | wc -l
        ls -l | sort
        cat file.txt | grep "pattern" > output.txt


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


### 9. Package Management and Services
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

