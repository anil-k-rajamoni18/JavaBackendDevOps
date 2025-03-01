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
    Working Directory → git add → Staging Area → git commit → Local Repository -> git push -> Remote Repo

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

    1. Fast-Forward Merge:

        Happens when the current branch is directly ahead of the branch you're merging, with no diverging commits.
        Example: git merge feature (if master has no new commits since feature was created).

    2. Three-Way Merge:

        Occurs when the branches being merged have diverged, and Git needs to combine their changes.
        Example: git merge feature (if master and feature have separate commits).

    3. Recursive Merge:

        Git’s default merge strategy, combining the changes of two branches by using their common ancestor.
        Example: git merge feature (with multiple divergent commits on both branches).

    4. Octopus Merge:

        Used to merge more than two branches at once.
        Example: git merge feature1 feature2 feature3.

    5. Merge with Conflicts:

        Happens when Git can't automatically resolve changes between branches, requiring manual intervention.
        Example: git merge feature (if there are conflicting changes in both branches).

7. Worktree (git worktree)
    
    - Work on multiple branches simultaneously in separate directories.
        git worktree add ../feature-branch feature-branch  # Create a linked directory
        git worktree list  # List all worktrees
        git worktree remove ../feature-branch  # Clean up

8. Advanced Diff & Log



### Excercise

1. Initialize a Git Repository
    Navigate to your git-demo folder and initialize a Git repository:
        cd git-demo
        git init
    
    Create 3 files and add some content:
        - Example.py
        - Sample.java
        - version.yml 

2. Check the Status of the Repository
    git status
    You should see the three files as untracked files.
    
3. Add Files to the Staging Area
    git add Example.java Sample.py version.yml 

4. Commit the Changes
    git commit -m "Initial commit: Added py java yml files"

5. Check the Commit History
    git log

6. Create a New Branch
    Create a new branch called feature-branch:
        git branch feature-branch

7. Switch to the New Branch
    Switch to the feature-branch:
        git checkout feature-branch
    
    Alternatively, you can create and switch to the branch in one command:
        git checkout -b feature-branch

8. Push the changes:
    git push -u origin feature-branch

10. Merge Changes Back to the Main Branch
    Switch back to the main branch and merge the changes from feature-branch:
        git checkout main
        git merge feature-branch

11. View Differences Between Branches
    View the differences between the main branch and feature-branch:
        git diff main feature-branch
    
12. Push Changes to a Remote Repository
    If you have a remote repository (e.g., on GitHub), add the remote and push your changes:
    
    git remote add origin <remote-repository-URL>
    git push -u origin main


14. Pull Changes from a Remote Repository
    If there are changes in the remote repository, pull them to your local repository:
        git pull origin main

15. Revert Changes
    If you want to undo the last commit (but keep the changes in your working directory):
        git reset --soft HEAD~1
    If you want to completely discard the last commit:
        git reset --hard HEAD~1

16. Stash Changes
    If you want to temporarily save changes without committing them:
        git stash
    To reapply the stashed changes:
        git stash apply


17. Tag a Commit
    Create a tag for a specific commit (e.g., for a release):
        git tag v1.0
    Push the tag to the remote repository:
        git push origin v1.0

18. Clone a Repository
    If you want to clone your repository to another location:
        git clone <remote-repository-URL>


19. Delete a Branch
    Delete the feature-branch after merging:
        git branch -d feature-branch

20. Checkout a Specific Commit
    Checkout a specific commit using its hash (from git log):
        git checkout <commit-hash>

21. Rebase a Branch
    If you want to rebase feature-branch onto main:
    
        git checkout feature-branch
        git rebase main

22. View Remote Repositories
    View the list of remote repositories:
        git remote -v

23. Fetch Changes from Remote
    Fetch changes from the remote repository without merging:
        git fetch origin

24. Rename a Branch
    Rename the current branch:
        git branch -m new-branch-name

25. Delete a Remote Branch
    Delete a branch on the remote repository:
        git push origin --delete branch-name
        
