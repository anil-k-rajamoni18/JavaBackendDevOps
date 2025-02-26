### 1. What is Git and GitHub?
Git:

    - A distributed version control system (DVCS) that tracks changes to files.
    - Enables collaboration, history tracking, and code recovery.
    - Works locally (no internet required).

GitHub:

    - A cloud-based platform for hosting Git repositories.
    - Adds collaboration features like pull requests, issues, and code reviews.
    - Acts as a remote backup and sharing hub.

### 2. Installation & Configuration
Install Git:

    Windows: Download from git-scm.com.
    Mac: Use brew install git (via Homebrew) or download from the website.
    Linux: sudo apt-get install git (Debian) or sudo yum install git (Red Hat).

Configure Git:

    # Set username and email (used in commits)
    git config --global user.name "Your Name"
    git config --global user.email "your@email.com"

    # Enable color output
    git config --global color.ui auto


### 3. Basic Commands Overview

    git init	            :    Initialize a new Git repo in the current directory
    git clone <url>	            : Copy a remote repo to your local machine
    git add <file>	            : Stage changes for commit
    git commit -m "message"	    : Save staged changes with a message
    git status	                : Show modified/staged files
    git log	                    : Display commit history
    git remote add origin <url>	: Link local repo to a remote (e.g., GitHub)
    git push origin main	    : Upload local commits to a remote branch

### 4. Git Stages
    Working Directory: Files you’re actively editing.
    Staging Area (Index): Files marked to be saved in the next commit (git add).
    Repository: Committed changes stored permanently (.git directory).

Flow:
    Working Directory → git add → Staging Area → git commit → Repository

### 5. Branching & Merging

    Branch: Isolated line of development (e.g., feature/login, bugfix/header).

        git branch <branch-name>     # Create a branch
        git checkout <branch-name>  # Switch to branch
        git checkout -b <branch>    # Create + switch to branch

    Merge: Combine changes from one branch into another.
        git checkout main           # Switch to target branch
        git merge <branch-name>     # Merge changes into main


### 6. Other Key Commands

    Stash: Temporarily save uncommitted changes.
        git stash        # Save changes
        git stash pop    # Restore changes

    Tags: Mark specific commits (e.g., releases).
        git tag v1.0.0         # Create a lightweight tag
        git tag -a v1.0.0 -m "Release 1.0.0"  # Annotated tag
        git push --tags        # Push tags to remote

    Fetch vs. Pull:
        git fetch: Download remote changes without merging.

        git pull: Fetch + merge (git pull = git fetch + git merge).
    
    Reset vs. Revert:
        git reset --hard HEAD~1: Discard the last commit (use with caution!).
        git revert <commit-id>: Create a new commit undoing a previous commit (safer).

    Rebase: Rewrite commit history (alternative to merging).
        git checkout feature
        git rebase main     # Apply feature commits on top of main