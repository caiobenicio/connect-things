'use strict';

angular.module('homeon').filter('formatPermission', function() {
	return function(input) {
		switch (input) {
		case 'ROLE_ADMIN':
			return 'Administrator';
			break;

		case 'ROLE_USER':
			return 'User';
			break;

		case 'ROLE_SHADOW':
			return 'Shadow';
			break;

		default:
			return 'Unknown';
			break;
		}
		;
	};
});
