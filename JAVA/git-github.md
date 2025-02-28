### 1. What is Git and GitHub?
Git:

    - A distributed version control system (DVCS) that tracks changes to files.
    - Enables collaboration, history tracking, and code recovery.
    - Works locally (no internet required).
    - Key Features: Local operations, integrity via SHA-1 hashing, non-linear development (branches).

GitHub:

    - A cloud-based platform for hosting Git repositories.
    - Adds collaboration features like pull requests, issues, and code reviews.
    - Acts as a remote backup and sharing hub.
    - Differentiation: Git is the tool; GitHub is a service built around Git.

### 2. Installation & Configuration
Install Git:

    Windows: Download from git-scm.com.
    Mac: Use brew install git (via Homebrew) or download from the website.
    Linux: sudo apt-get install git (Debian) or sudo yum install git (Red Hat).

Configure Git:

    # Set username and email (used in commits)
        git config --global user.name "Your Name"
        git config --global user.email "email@example.com"
        # Set default branch to 'main'
        git config --global init.defaultBranch main
        # View settings
        git config --list

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
    
        -> Resolve Conflicts: Manually edit conflicted files, then git add and git commit.


### 6. Other Key Commands

    Stash: Temporarily save uncommitted changes.
        git stash save "message"    # Save changes
        git stash list              # List stashes
        git stash apply             # Restore latest stash

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


### 7.  Collaboration on GitHub

    Workflow:
        Fork a repo (copy to your GitHub account).
        Clone your fork: git clone https://github.com/your-username/repo.git.
        Add Upstream Remote:
            git remote add upstream https://github.com/original/repo.git
        Sync with upstream:
            git fetch upstream
            git merge upstream/main
        Push changes to your fork and create a Pull Request (PR).
        Review PRs: Discuss, test, and approve changes before merging.

### 8. GitHub Best Practices
    
    - Commit Messages: Use imperative mood ("Fix bug" vs. "Fixed bug"), keep them concise.
    - Branching Strategy:
        Git Flow: main, develop, feature branches, releases.
        GitHub Flow: Short-lived branches merged via PRs.
    - Code Hygiene:
        Use .gitignore to exclude build files, logs, etc.
        Atomic commits (one logical change per commit).
    - Collaboration:
        Protect main branch (require PR reviews).
        Regularly git pull to avoid conflicts.
        Use issues and projects for task tracking.
    - SSH Keys: Use for secure authentication (GitHub Guide).
    - Aliases: Save time with shortcuts (e.g., git config --global alias.co checkout).


### 9. Advance commands
1. Interactive Rebase (git rebase -i)
    - Rewrite commit history (squash, reword, reorder, or drop commits).
        git rebase -i HEAD~5  # Edit last 5 commits
    - Common Actions:
        squash: Combine commits.
        fixup: Merge commits and discard their messages.
        reword: Edit commit messages.
    - git rebase --autostash to stash changes before rebasing and reapply them afterward.

2. Stash with Options

    - Stash Untracked Files:
        git stash push -u  # Include untracked files
    
    - List/Apply Specific Stash:
        git stash list
        git stash apply stash@{2}  # Apply a specific stash

    - Create Branch from Stash:
        git stash branch <branch-name> stash@{1}

3. Cherry-Pick (git cherry-pick)

    -  Apply a specific commit from another branch to your current branch.
        git cherry-pick <commit-hash>  # Apply a single commit
        git cherry-pick <start-hash>^..<end-hash>  # Range of commits
    - Fix conflicts manually, then git cherry-pick --continue

4. Reflog (git reflog)

    - Recover lost commits/branches or undo destructive operations.
        git reflog  # Show all HEAD movements
        git checkout HEAD@{5}  # Checkout a previous state
        git reset --hard HEAD@{3}  # Reset to a specific entry

5. Bisect (git bisect)

    - Find the commit that introduced a bug.
        git bisect start
        git bisect bad        # Mark current commit as bad
        git bisect good v1.0  # Mark a known good commit
        # Git checks out a midpoint; test and mark as good/bad:
        git bisect good  # or git bisect bad
        git bisect reset  # Exit bisect mode

6. Advanced Merging

    - Octopus Merge: Merge multiple branches in one commit.
        git merge branch1 branch2 branch3
    
    - Merge with Strategies:
        git merge -s ours/theirs  # Resolve conflicts by favoring one side

7. Worktree (git worktree)
    
    - Work on multiple branches simultaneously in separate directories.
        git worktree add ../feature-branch feature-branch  # Create a linked directory
        git worktree list  # List all worktrees
        git worktree remove ../feature-branch  # Clean up

8. Advanced Diff & Log