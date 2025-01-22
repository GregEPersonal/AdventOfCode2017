# Solution for Day 24

require_relative "../utils"
require "pry"

# Read input lines
lines = read_input_lines("day_24/input.txt")

components = []

lines.each do |line|
  components << line.split("/").map(&:to_i)
end

def find_strongest_bridge(components, current_port, current_strength)
  possible_next_components = components.select do |component|
    component.include?(current_port)
  end

  if possible_next_components.empty?
    return current_strength
  end

  possible_next_components.map do |component|
    next_port = component[0] == current_port ? component[1] : component[0]
    new_components = components.dup
    new_components.delete(component)
    find_strongest_bridge(new_components, next_port, current_strength + component[0] + component[1])
  end.max
end

puts find_strongest_bridge(components, 0, 0)
