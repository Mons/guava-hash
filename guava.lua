local ffi = require "ffi"
local pcall = _G.pcall
module ("guava")

ffi.cdef [[
	int guava(long, unsigned);
]]

local libguava

do
	local r,e = pcall(function() return ffi.load("guava") end)
	if r then libguava = e
	else
	r,e = pcall(function() return ffi.load("./libguava.so") end)
	if r then libguava = e
	else
	error(e)
	end end
end

function guava(key, buckets)
	return libguava.guava(key,buckets)
end

return guava
