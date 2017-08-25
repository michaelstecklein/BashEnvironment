set number
set mouse=a " nic
syntax enable
set hlsearch
set undodir=$HOME/.vim/undo
set undofile

" show existing tab with 4 spaces width
set tabstop=4
" when indenting with '>', use 4 spaces width
set shiftwidth=4
" On pressing tab, insert 4 spaces
"set expandtab

execute pathogen#infect()
