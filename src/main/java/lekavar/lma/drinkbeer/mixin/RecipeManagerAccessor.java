//Copyright (c) 2020 possible_triangle. All Rights Reserved.
//
//Permissions are hereby granted below to any person having a copy of
//this software and/or its associated files:
//
//Usage - You may use this software for private and public use. You may use this software as a library
//or integrate it with your code with the predicate being it must be downloaded from our sources and a soft or hard dependency.
//Copying - You may copy this software for private use or to contribute to this software.
//Modification - You may modify this software for private use or to contribute to this software.
//Publishing - You may not publish copies of this software.
//Distributing - You may not distribute this software.
//Sublicensing - You may not sublicense this software.
//Selling - You may not sell this software.
//
//Modpack Clarification:
//Permission is granted for Modpacks to include this software as long
//as the copy of this software in the pack is an unmodified public copy
//from our public sources and is marked as included in the Modpack.
//
//Definition of 'Our Sources' in this license:
//Our Sources in this license means a copy
//of this software or its associated files that come
//directly from the owners of this software, an example
//are the files uploaded by us on our Curse Page.
//
//The above copyright notice and these permission notices shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

package lekavar.lma.drinkbeer.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RecipeManager.class)
public interface RecipeManagerAccessor {

    @Accessor
    void setByName(Map<ResourceLocation, Recipe<?>> recipes);

    @Accessor
    Map<ResourceLocation, Recipe<?>> getByName();

    @Accessor
    void setRecipes(Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> recipes);

}
