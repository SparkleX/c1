module.exports = function(grunt) {
	var timestamp = grunt.template.today("yyyymmdd'T'HHMMss'Z'L");
	// Project configuration.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		uglify: {
			options: {
				banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
			},
			build: {
				src: 'src/test.js',
				dest: 'build/test.min.js'
			}
		},
		cacheBust: {
			taskName: {
				options: {
					baseDir: './',
					assets: ['public/**'],
					hash: timestamp,
					jsonOutput:true ,
					queryString: true,
					createCopies: true,									
					outputDir: 'public'   
				},
				files: [{   
					expand: true,
					cwd: 'public/',
					src: ['**/*.html','**/*.js']
				}]
			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-cache-bust');
	grunt.registerTask('default', ['uglify', 'cacheBust']);

};