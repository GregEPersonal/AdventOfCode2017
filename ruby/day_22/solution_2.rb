# Solution for Day 21

require_relative "../utils"
require "pry"

# Read input lines
lines = read_input_lines("day_22/input.txt")

infected_nodes = {}

# Okay, we're going to read this in where the current node is the center of the grid, at 0,0
# Up is negative, down is positive, left is negative, right is positive
grid_height = lines.length / 2

lines.each_with_index do |line, index|
  line.split("").each_with_index do |char, inner_index|
    location_key = [inner_index - grid_height, index - grid_height].join(",")
    infected_nodes[location_key] = "infected" if char == "#"
  end
end

virus_direction = "up"
virus_location = [0, 0]

def turn_left(direction)
  case direction
  when "up"
    return "left"
  when "left"
    return "down"
  when "down"
    return "right"
  when "right"
    return "up"
  end
end

def turn_right(direction)
  case direction
  when "up"
    return "right"
  when "right"
    return "down"
  when "down"
    return "left"
  when "left"
    return "up"
  end
end

def turn_around(direction)
  case direction
  when "up"
    return "down"
  when "down"
    return "up"
  when "left"
    return "right"
  when "right"
    return "left"
  end
end

def coords_from_direction(direction)
  case direction
  when "up"
    return [0, -1]
  when "down"
    return [0, 1]
  when "left"
    return [-1, 0]
  when "right"
    return [1, 0]
  end
end

def print_grid(grid, virus_location)
  # Given a hash of keys of the form "x,y", get the minimum and maximum x and y values
  min_x = grid.keys.map { |key| key.split(",")[0].to_i }.min
  max_x = grid.keys.map { |key| key.split(",")[0].to_i }.max
  min_y = grid.keys.map { |key| key.split(",")[1].to_i }.min
  max_y = grid.keys.map { |key| key.split(",")[1].to_i }.max

  (min_y..max_y).each do |y|
    (min_x..max_x).each do |x|
      location_key = [x, y].join(",")
      if virus_location == [x, y]
        print "V"
      elsif grid[location_key] == "infected"
        print "#"
      elsif grid[location_key] == "weakened"
        print "W"
      elsif grid[location_key] == "flagged"
        print "F"
      else
        print "."
      end
    end
    puts
  end
  puts
end

def virus_burst(virus_location, virus_direction, infected_nodes)
  new_virus_count = 0
  location_key = virus_location.join(",")

  debug_print = false

  if infected_nodes[location_key] == "flagged"
    puts "Current location is flagged, turning around" if debug_print
    puts "Virus location: #{virus_location}" if debug_print

    # delete the node
    infected_nodes.delete(location_key)
    virus_direction = turn_around(virus_direction)
  elsif infected_nodes[location_key] == "weakened"
    puts "Current location is weakened, not changing direction" if debug_print
    puts "Virus location: #{virus_location}" if debug_print

    infected_nodes[location_key] = "infected"
    new_virus_count += 1
    # Don't change direction
  elsif infected_nodes[location_key] == "infected"
    puts "Current location is infected, turning right" if debug_print
    puts "Virus location: #{virus_location}" if debug_print

    infected_nodes[location_key] = "flagged"
    virus_direction = turn_right(virus_direction)
  else # Clean
    puts "Current location is clean, turning left" if debug_print
    puts "Virus location: #{virus_location}" if debug_print

    infected_nodes[location_key] = "weakened"
    virus_direction = turn_left(virus_direction)
  end

  print_grid(infected_nodes, virus_location) if debug_print
  binding.pry if debug_print

  coords = coords_from_direction(virus_direction)
  virus_location[0] += coords[0]
  virus_location[1] += coords[1]

  [virus_location, virus_direction, infected_nodes, new_virus_count]
end

iterations = 10000000
total_virus_count = 0
print_grid(infected_nodes, virus_location)
iterations.times do |i|
  puts "At iteration #{i}" if i % 100000 == 0
  virus_location, virus_direction, infected_nodes, new_virus_count = virus_burst(virus_location, virus_direction, infected_nodes)
  total_virus_count += new_virus_count
end

# print_grid(infected_nodes, virus_location)
puts "Final Virus infections: #{total_virus_count}"
