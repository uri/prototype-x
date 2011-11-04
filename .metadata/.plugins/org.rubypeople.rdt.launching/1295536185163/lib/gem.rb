=begin
------------------------------------------------------- Class: GemPlugin
     Implements a dynamic plugin loading, configuration, and discovery
     system based on RubyGems and a simple additional name space that
     looks like a URI.

     A plugin is created and put into a category with the following
     code:

      class MyThing < GemPlugin::Plugin "/things"
        ...
      end

     What this does is sets up your MyThing in the plugin registry via
     GemPlugin::Manager. You can then later get this plugin with
     GemPlugin::Manager.create("/things/mything") and can also pass in
     options as a second parameter.

     This isn't such a big deal, but the power is really from the
     GemPlugin::Manager.load method. This method will go through the
     installed gems and require_gem any that depend on the gem_plugin
     RubyGem. You can arbitrarily include or exclude gems based on what
     they also depend on, thus letting you load these gems when
     appropriate.

     Since this system was written originally for the Mongrel project
     that'll be the best example of using it.

     Imagine you have a neat plugin for Mongrel called snazzy_command
     that gives the mongrel_rails a new command snazzy (like:
     mongrel_rails snazzy). You'd like people to be able to grab this
     plugin if they want and use it, because it's snazzy.

     First thing you do is create a gem of your project and make sure
     that it depends on "mongrel" AND "gem_plugin". This signals to the
     GemPlugin system that this is a plugin for mongrel.

     Next you put this code into a file like lib/init.rb (can be
     anything really):

      class Snazzy < GemPlugin::Plugin "/commands"
        ...
      end

     Then when you create your gem you have the following bits in your
     Rakefile:

      spec.add_dependency('mongrel', '>= 0.3.9')
      spec.add_dependency('gem_plugin', '>= 0.1')
      spec.autorequire = 'init.rb'

     Finally, you just have to now publish this gem for people to
     install and Mongrel will "magically" be able to install it.

     The "magic" part though is pretty simple and done via the
     GemPlugin::Manager.load method. Read that to see how it is really
     done.

------------------------------------------------------------------------


Constants:
----------
     EXCLUDE: true
     INCLUDE: false


Class methods:
--------------
     Plugin

=end
module Gem

  def self.post_install_hooks
  end

  def self.ruby
  end

  def self.time(arg0, arg1, arg2, *rest)
  end

  def self.user_dir
  end

  def self.configuration
  end

  def self.location_of_caller
  end

  def self.refresh
  end

  def self.pre_uninstall
  end

  def self.ensure_gem_subdirectories(arg0)
  end

  def self.datadir(arg0)
  end

  def self.platforms
  end

  def self.path
  end

  def self.suffix_pattern
  end

  def self.configuration=(arg0)
  end

  def self.config_file
  end

  def self.post_uninstall
  end

  def self.load_path_insert_index
  end

  def self.pre_uninstall_hooks
  end

  def self.default_bindir
  end

  def self.default_system_source_cache_dir
  end

  def self.default_path
  end

  def self.platforms=(arg0)
  end

  def self.ui
  end

  def self.default_sources
  end

  def self.latest_load_paths
  end

  def self.loaded_specs
  end

  def self.source_index
  end

  def self.post_uninstall_hooks
  end

  def self.win_platform?
  end

  def self.ruby_version
  end

  def self.post_install
  end

  def self.marshal_version
  end

  def self.bin_path(arg0, arg1, arg2, *rest)
  end

  def self.default_exec_format
  end

  def self.searcher
  end

  def self.available?(arg0, arg1, arg2, *rest)
  end

  def self.inflate(arg0)
  end

  def self.promote_load_path(arg0, arg1)
  end

  def self.prefix
  end

  def self.gunzip(arg0)
  end

  def self.use_paths(arg0, arg1, arg2, *rest)
  end

  def self.binary_mode
  end

  def self.dir
  end

  def self.ruby_engine
  end

  def self.user_home
  end

  def self.activate(arg0, arg1, arg2, *rest)
  end

  def self.suffixes
  end

  def self.all_load_paths
  end

  def self.cache
  end

  def self.clear_paths
  end

  def self.pre_install
  end

  def self.sources
  end

  def self.required_location(arg0, arg1, arg2, arg3, *rest)
  end

  def self.default_user_source_cache_dir
  end

  def self.bindir(arg0, arg1, *rest)
  end

  def self.gzip(arg0)
  end

  def self.pre_install_hooks
  end

  def self.sources=(arg0)
  end

  def self.default_dir
  end

  def self.deflate(arg0)
  end

  def self.read_binary(arg0)
  end

  def self.find_files(arg0)
  end

end
