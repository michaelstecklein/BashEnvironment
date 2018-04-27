#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>
#include <ctype.h>

#define OUTPUT_FILE	"search-results.txt"
#define MAX_CMD_LEN 100
#define COMMAND "sudo find / -xdev -type f -print0 | sudo xargs -0 grep -H \"%s.\\\?%s.\\\?%s\" > %s"

int main()
{
	// get user input
	char* num = getpass("Enter num: ");
	if (strlen(num) != 9) {
		printf("Enter number of length 9\n");
		exit(1);
	}
	for (int i = 0; i < strlen(num); i++) {
		char c = num[i];
		if (!isdigit(c)) {
			printf("Entry must be a number\n");
			exit(1);
		}
	}

	// parse input
	char* first = (char*) malloc(3+1);
	char* second = (char*) malloc(2+1);
	char* third = (char*) malloc(4+1);
	memset(first, 0, 3+1);
	memset(second, 0, 2+1);
	memset(third, 0, 4+1);
	memcpy(first, &num[0], 3);
	memcpy(second, &num[3], 2);
	memcpy(third, &num[5], 4);
	memset(num, 0, strlen(num));

	// build grep command
	char cmd[MAX_CMD_LEN];
	sprintf(cmd, COMMAND, first, second, third, OUTPUT_FILE);

	// run command
	printf("Running grep of /, output can be found in: %s\nYou may be prompted for you system password\n", OUTPUT_FILE);
	system(cmd);

	// clear contents and dealloc
	memset(cmd, 0, MAX_CMD_LEN);
	memset(first, 0, 3+1);
	memset(second, 0, 2+1);
	memset(third, 0, 4+1);
	free(first);
	free(second);
	free(third);
}
