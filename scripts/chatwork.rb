unless ARGV[1].empty?
	require "chatwork"

  # Create message
  ChatWork.api_key = "INSERT_CHATWORK_KEY_HERE"
  ChatWork::Message.create(room_id: "#{ARGV[0]}", body: "[info]Cố lến anh, yêu anh vl :x:p \nLink : #{ARGV[1]}[hr]#{ARGV[2]}[/info]")
end
